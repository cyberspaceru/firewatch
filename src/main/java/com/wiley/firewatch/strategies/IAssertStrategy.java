package com.wiley.firewatch.strategies;

import com.wiley.firewatch.api.enities.ProcessingEntries;

import java.util.List;

/**
 * Created by itatsiy on 4/24/2018.
 */
public interface IAssertStrategy {
    void execute(List<ProcessingEntries> processing);
}
