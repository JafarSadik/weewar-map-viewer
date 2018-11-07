package com.github.weewar.mapviewer.model.enums

enum class UnitType {
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

    companion object {
        fun of(str: String?): UnitType? {
            return if (str != null) valueOf(str) else null
        }
    }
}
