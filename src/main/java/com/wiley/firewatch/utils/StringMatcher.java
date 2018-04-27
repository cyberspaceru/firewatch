package com.wiley.firewatch.utils;

import com.wiley.firewatch.observers.MatchingType;

import java.util.regex.Pattern;

/**
 * Created by itatsiy on 4/27/2018.
 */
public class StringMatcher {
    public static boolean match(String actual, MatchingType type, String expected) {
        if (actual == null && expected == null) {
            return true;
        } else if (actual == null || expected == null) {
            return false;
        } else {
            switch (type) {
                case REGEXP:
                    return Pattern.compile(expected).matcher(actual).find();
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
