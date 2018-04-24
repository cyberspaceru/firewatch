package com.wiley.firewatch.api.enities;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by itatsiy on 4/23/2018.
 */
@Accessors(fluent = true)
public class ProcessingMetadata<T> {
    @Getter
    private Map<ObserverMetadata<T>, Boolean> processingTable = new HashMap<>();

    public double overlap() {
        return processingTable().values().stream().filter(x -> x).count() / processingTable.size();
    }

    public boolean finished() {
        return overlap() == 1.0d;
    }
}
