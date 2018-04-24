package com.wiley.firewatch.exceptions;

/**
 * Created by itatsiy on 4/20/2018.
 */
public class FirewatchConnectionUnavailableException extends RuntimeException {
    public FirewatchConnectionUnavailableException() {
    }

    public FirewatchConnectionUnavailableException(String message) {
        super(message);
    }

    public FirewatchConnectionUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public FirewatchConnectionUnavailableException(Throwable cause) {
        super(cause);
    }

    public FirewatchConnectionUnavailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
