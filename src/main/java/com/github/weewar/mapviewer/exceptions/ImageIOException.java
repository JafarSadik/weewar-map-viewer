package com.github.weewar.mapviewer.exceptions;

public class ImageIOException extends RuntimeException {
    public ImageIOException() {
        super();
    }

    public ImageIOException(String message) {
        super(message);
    }

    public ImageIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageIOException(Throwable cause) {
        super(cause);
    }
}
