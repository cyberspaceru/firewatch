package com.wiley.firewatch.api.enities;

import com.wiley.firewatch.strategies.IAssertStrategy;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by itatsiy on 4/23/2018.
 */
@Data
@Accessors(fluent = true)
public class FirewatchAPIContext {
    private boolean inverted;
    private IAssertStrategy strategy;

    public boolean inverted() {
        if (inverted) {
            inverted(false);
            return true;
        }
        return false;
    }
}
