package com.wiley.firewatch.exceptions;

import static java.util.Optional.ofNullable;

/**
 * Created by itatsiy on 4/20/2018.
 */
public class FirewatchUnexpectedObservableTypeException extends RuntimeException {
    public FirewatchUnexpectedObservableTypeException(Class clazz) {
        super("Unexpected observable type[" + ofNullable(clazz).map(Class::getSimpleName).orElse("null") + "].");
    }
}
