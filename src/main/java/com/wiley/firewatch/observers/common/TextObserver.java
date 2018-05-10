package com.wiley.firewatch.observers.common;

import java.util.function.BiPredicate;

/**
 * Created by itatsiy on 4/28/2018.
 */
public abstract class TextObserver {
    private final String expected;
    private final BiPredicate<String, String> predicate;

    public TextObserver(String expected, BiPredicate<String, String>predicate) {
        this.expected = expected;
        this.predicate = predicate;
    }

    protected boolean observeText(String actual) {
        return predicate.test(actual, expected);
    }

    @Override
    public String toString() {
        return "Text(" + expected + ")";
    }
}
