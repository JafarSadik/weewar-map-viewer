package com.github.weewar.mapviewer.exceptions;

public class ImageResizeException extends RuntimeException {
    public ImageResizeException() {
        super();
    }

    public ImageResizeException(String message) {
        super(message);
    }

    public ImageResizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageResizeException(Throwable cause) {
        super(cause);
    }
}
