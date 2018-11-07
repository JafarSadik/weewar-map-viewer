package com.github.weewar.mapviewer.model.enums

enum class TerrainType {
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

    companion object {
        fun of(str: String): TerrainType = valueOf(str)
    }
}

