package com.wiley.firewatch.observers;

/**
 * Created by itatsiy on 4/20/2018.
 */
public abstract class StringParamObserver<T> implements IObserver<T> {
    protected final MatchingType type;

    public StringParamObserver(MatchingType type) {
        this.type = type;
    }

    protected boolean match(String actual, String expected) {
        if (actual == null && expected == null) {
            return true;
        } else if (actual == null || expected == null) {
            return false;
        } else {
            switch (type) {
                case REGEXP:
                    return actual.matches(expected);
                case EQUALS:
                    return actual.equals(expected);
                case EQUALS_IGNORE_CASE:
                    return actual.equalsIgnoreCase(expected);
                case CONTAINS:
                    return actual.contains(expected);
                case START_WITH:
                    return actual.startsWith(expected);
                case END_WITH:
                    return actual.endsWith(expected);
            }
        }
        return false;
    }
}
