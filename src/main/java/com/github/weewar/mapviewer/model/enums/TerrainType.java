package com.github.weewar.mapviewer.model.enums;

public enum TerrainType {
    airfield,
    bridge,
    city,
    desert,
    forest,
    harbor,
    mountain,
    plain,
    repairshop,
    swamp,
    water;

    public static TerrainType of(String str) {
        return str != null ? valueOf(str) : null;
    }
}

