package com.wiley.firewatch.api.enities;

import com.wiley.firewatch.api.FirewatchRequest;
import com.wiley.firewatch.api.FirewatchResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by itatsiy on 4/24/2018.
 */
public class ProcessingEntries extends ArrayList<ProcessingEntry> {
    private final FirewatchRequest firewatchRequest;
    private final FirewatchResponse firewatchResponse;

    public ProcessingEntries(int initialCapacity, FirewatchRequest firewatchRequest, FirewatchResponse firewatchResponse) {
        super(initialCapacity);
        this.firewatchRequest = firewatchRequest;
        this.firewatchResponse = firewatchResponse;
    }

    public ProcessingEntries(FirewatchRequest firewatchRequest, FirewatchResponse firewatchResponse) {
        this.firewatchRequest = firewatchRequest;
        this.firewatchResponse = firewatchResponse;
    }

    public ProcessingEntries(Collection<? extends ProcessingEntry> c, FirewatchRequest firewatchRequest, FirewatchResponse firewatchResponse) {
        super(c);
        this.firewatchRequest = firewatchRequest;
        this.firewatchResponse = firewatchResponse;
    }

    public boolean finished() {
        ProcessingEntry best = best();
        return best != null && (best.request() == null || best.request().finished()) && (best.response() == null || best.response().finished());
    }

    public ProcessingEntry best() {
        return bests() == null ? null : bests().findFirst().orElse(null);
    }

    public Stream<ProcessingEntry> bests() {
        ProcessingEntry best = bestOverlap();
        if (best == null) {
            return null;
        }
        return stream().filter(x -> (x.request() == best.request() || x.request().overlap() == best.request().overlap())
                && (x.response() == best.response() || x.response().overlap() == best.response().overlap()));
    }

    public ProcessingEntry bestOverlap() {
        return stream().max(ProcessingEntry::compareTo).orElse(null);
    }

}
