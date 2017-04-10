package com.goeuro.exception;

public abstract class GoEuroException extends Exception {
    public GoEuroException() {
    }

    public GoEuroException(String message) {
        super(message);
    }

    public GoEuroException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoEuroException(Throwable cause) {
        super(cause);
    }

    public GoEuroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
