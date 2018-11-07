package com.github.weewar.mapviewer.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.weewar.mapviewer.model.enums.Direction
import com.github.weewar.mapviewer.model.enums.Owner
import com.github.weewar.mapviewer.model.enums.TerrainType
import com.github.weewar.mapviewer.model.enums.UnitType

class Tile @JsonCreator
constructor(@JsonProperty("x") val x: Int, @JsonProperty("y") val y: Int, @JsonProperty("type") val type: TerrainType,
            @JsonProperty("startFaction") val startFaction: Owner?, @JsonProperty("direction") val direction: Direction?,
            @JsonProperty("unit") val unit: UnitType?, @JsonProperty("unitOwner") val unitOwner: Owner?) {

    override fun toString(): String {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                ", startFaction=" + startFaction +
                ", direction=" + direction +
                ", unit=" + unit +
                ", unitOwner=" + unitOwner +
                '}'.toString()
    }
}
