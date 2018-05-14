package com.wiley.firewatch.strategies;

import com.wiley.firewatch.api.enities.ProcessingEntries;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.StrBuilder;
import org.testng.Assert;

import java.util.List;
import java.util.Optional;

/**
 * Created by itatsiy on 4/24/2018.
 */
@Slf4j
public class BaseAssertStrategy implements IAssertStrategy {

    private final String errorMessage;

    public BaseAssertStrategy(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(List<ProcessingEntries> processing) {
        for (ProcessingEntries entries : processing) {
            if (!entries.finished()) {
                StrBuilder details = new StrBuilder();
                Optional.ofNullable(entries.firewatchRequest()).ifPresent(x -> details.append("\n").append(x.toString()));
                Optional.ofNullable(entries.firewatchResponse()).ifPresent(x -> details.append("\n").append(x.toString()));
                Assert.fail(errorMessage + details.toString());
            }
        }
    }
}
