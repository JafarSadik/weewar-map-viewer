package com.github.weewar.mapviewer.exceptions;

public class MapParseException extends RuntimeException {
    public MapParseException() {
        super();
    }

    public MapParseException(String message) {
        super(message);
    }

    public MapParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MapParseException(Throwable cause) {
        super(cause);
    }
}
