package com.github.weewar.mapviewer.exceptions;

public class ClassPathResourceException extends RuntimeException {
    public ClassPathResourceException() {
        super();
    }

    public ClassPathResourceException(String message) {
        super(message);
    }

    public ClassPathResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassPathResourceException(Throwable cause) {
        super(cause);
    }
}
