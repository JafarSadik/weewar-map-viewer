package com.github.weewar.mapviewer.model.enums;

public enum Direction {
    ew,
    nesw,
    nwse;

    public static Direction of(String str) {
        return str != null ? valueOf(str) : null;
    }
}
