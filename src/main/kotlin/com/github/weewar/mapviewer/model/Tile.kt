package com.github.weewar.mapviewer.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.weewar.mapviewer.model.enums.*

data class Tile(
        @JsonProperty("x") val x: Int,
        @JsonProperty("y") val y: Int,
        @JsonProperty("type") val type: TerrainType,
        @JsonProperty("startFaction") val startFaction: Owner?,
        @JsonProperty("direction") val direction: Direction?,
        @JsonProperty("unit") val unit: UnitType?,
        @JsonProperty("unitOwner") val unitOwner: Owner?
)
