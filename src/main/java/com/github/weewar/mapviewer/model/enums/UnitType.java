package com.github.weewar.mapviewer.model.enums;

public enum UnitType {
    antiAircraft,
    assaultArtillery,
    battleship,
    berserker,
    bomber,
    destroyer,
    dfa,
    heavyArtillery,
    heavyTank,
    heavyTrooper,
    helicopter,
    hovercraft,
    jet,
    lightArtillery,
    raider,
    speedboat,
    sub,
    tank,
    trooper;

    public static UnitType of(String str) {
        return str != null ? valueOf(str) : null;
    }
}
