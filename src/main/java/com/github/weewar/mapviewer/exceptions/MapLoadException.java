package com.github.weewar.mapviewer.exceptions;

public class MapLoadException extends RuntimeException {
    public MapLoadException() {
        super();
    }

    public MapLoadException(String message) {
        super(message);
    }

    public MapLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapLoadException(Throwable cause) {
        super(cause);
    }
}
