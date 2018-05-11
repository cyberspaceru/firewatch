package com.wiley.firewatch.api;

import com.wiley.firewatch.FirewatchConnection;
import com.wiley.firewatch.api.enities.ObserverMetadata;
import com.wiley.firewatch.api.enities.ProcessingEntries;
import com.wiley.firewatch.api.enities.ProcessingEntry;
import com.wiley.firewatch.api.enities.ProcessingMetadata;
import com.wiley.firewatch.api.enums.RelationshipType;
import com.wiley.firewatch.strategies.BaseAssertStrategy;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Assert execution.
 *
 * @param <T> Observable object.
 * @param <S> Current class to build fluent API.
 */
public class Firewatch<T, S extends FirewatchBlueprint> extends FirewatchBlueprint<T, S> {
    private static final long DEFAULT_TIMEOUT = 10000;

    Firewatch(FirewatchBlueprint parent, RelationshipType relationship) {
        super(parent, relationship);
    }

    public void executeWithTimeout() {
        executeWithTimeout(DEFAULT_TIMEOUT);
    }

    public void executeWithTimeout(long msTimeout) {
        long end = System.currentTimeMillis() + msTimeout;
        while (end > System.currentTimeMillis()) {
            try {
                execute();
                return;
            } catch (AssertionError ignored) {
                // AssertionError is ignored
            }
        }
        execute();
    }

    public void execute() {
        if (context().strategy() != null) {
            context().strategy().execute(process());
        }
        new BaseAssertStrategy().execute(process());
    }

    private List<ProcessingEntries> process() {
        List<HarEntry> hars = FirewatchConnection.proxyServer().getHar().getLog().getEntries();
        return buildAssert().entrySet().stream()
                .map(fPair -> process(fPair, hars))
                .collect(Collectors.toList());
    }

    /**
     * Map the All Firewatches to the HarEntries.
     */
    private static ProcessingEntries process(Entry<FirewatchRequest, FirewatchResponse> fPair, List<HarEntry> hars) {
        ProcessingEntries processingEntries = new ProcessingEntries(fPair.getKey(), fPair.getValue());
        hars.stream().map(har -> process(fPair, har)).forEach(processingEntries::add);
        return processingEntries;
    }

    /**
     * Map the Firewatches Pair to the HarEntry.
     */
    private static ProcessingEntry process(Entry<FirewatchRequest, FirewatchResponse> fPair, HarEntry har) {
        ProcessingMetadata<HarRequest> request = fPair.getKey() == null ? null : process(fPair.getKey(), har.getRequest());
        ProcessingMetadata<HarResponse> response = fPair.getValue() == null ? null : process(fPair.getValue(), har.getResponse());
        return new ProcessingEntry(request, response);
    }

    /**
     * Map the Firewatch to the Har.
     */
    private static <H> ProcessingMetadata<H> process(FirewatchBlueprint<H, ?> firewatch, H har) {
        ProcessingMetadata<H> processingMetadata = new ProcessingMetadata<>();
        for (ObserverMetadata<H> observerMetadata : firewatch.observers()) {
            try {
                boolean result = observerMetadata.observer().observe(har);
                result = observerMetadata.invert() != result;
                processingMetadata.processingTable().put(observerMetadata, result);
            } catch (Throwable ignore) {
                // ignored
            }
        }
        return processingMetadata;
    }

    private Map<FirewatchRequest, FirewatchResponse> buildAssert() {
        Map<FirewatchRequest, FirewatchResponse> result = new HashMap<>();
        FirewatchBlueprint cursor = this;
        while (cursor != null) {
            FirewatchRequest request = null;
            FirewatchResponse response = null;
            if (cursor instanceof FirewatchRequest) {
                request = (FirewatchRequest) cursor;
                if (cursor.child() != null && (cursor.child() instanceof FirewatchResponse && cursor.child().relationship() == RelationshipType.THEN)) {
                    response = (FirewatchResponse) cursor.child();
                }
            } else if (cursor instanceof FirewatchResponse && cursor.relationship() == RelationshipType.AND) {
                response = (FirewatchResponse) cursor;
            }
            if (request != null || response != null) {
                result.put(request, response);
            }
            cursor = cursor.parent();
        }
        return result;
    }
}
