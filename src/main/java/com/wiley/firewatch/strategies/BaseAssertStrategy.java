package com.wiley.firewatch.strategies;

import com.wiley.firewatch.api.enities.ProcessingEntries;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.List;

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
                Assert.fail(errorMessage);
            }
        }
    }
}
