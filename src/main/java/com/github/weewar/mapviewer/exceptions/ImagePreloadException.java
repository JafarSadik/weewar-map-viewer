package com.github.weewar.mapviewer.exceptions;

public class ImagePreloadException extends RuntimeException {
    public ImagePreloadException() {
        super();
    }

    public ImagePreloadException(String message) {
        super(message);
    }

    public ImagePreloadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImagePreloadException(Throwable cause) {
        super(cause);
    }
}
