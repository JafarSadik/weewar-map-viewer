package com.github.weewar.mapviewer.model.enums;

public enum Owner {
    blue,
    red,
    purple,
    yellow,
    green,
    white;

    public static Owner of(String str) {
        return str != null ? valueOf(str) : null;
    }
}
