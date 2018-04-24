package com.wiley.firewatch.exceptions;

/**
 * Created by itatsiy on 4/20/2018.
 */
public class FirewatchMalformedAssertException extends RuntimeException {
    public FirewatchMalformedAssertException() {
    }

    public FirewatchMalformedAssertException(String message) {
        super(message);
    }

    public FirewatchMalformedAssertException(String message, Throwable cause) {
        super(message, cause);
    }

    public FirewatchMalformedAssertException(Throwable cause) {
        super(cause);
    }

    public FirewatchMalformedAssertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
