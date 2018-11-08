package com.github.weewar.mapviewer.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.lang.Math.round

class MapHeader(@JsonProperty("id") val id: Int, @JsonProperty("name") val mapName: String, @JsonProperty("revision") val revision: Int,
                @JsonProperty("creator") val creator: String, @JsonProperty("players") val players: Int, @JsonProperty("width") val width: Int,
                @JsonProperty("perBaseCredits") val income: Int, @JsonProperty("initialCredits") val startCredits: Int,
                @JsonProperty("height") val height: Int) {

    @JsonIgnore
    fun size(): Pair<Int, Int> = Pair(width, height)

    @JsonIgnore
    fun sizeInPixels(): Pair<Int, Int> = with(MapConstants) {
        val maxHorizontalOffset = if (height > 1) hexHorizontalOffset else 0F
        val x = round(width * MapConstants.hexWidth + maxHorizontalOffset)
        val y = round(hexHeight + (height - 1) * hexVerticalDistance)
        return Pair(x, y)
    }

    fun hexToPixel(column: Int, row: Int): Pair<Int, Int> = with(MapConstants) {
        val rowHorizontalOffset = if (row % 2 != 0) hexHorizontalOffset else 0F
        val x = round(column * hexWidth + rowHorizontalOffset)
        val y = round(row * hexVerticalDistance)
        return Pair(x, y)
    }

    override fun toString(): String {
        return "MapHeader{" +
                "mapId=" + id +
                ", mapName='" + mapName + '\''.toString() +
                ", revision=" + revision +
                ", creator='" + creator + '\''.toString() +
                ", players=" + players +
                ", startCredits=" + startCredits +
                ", income=" + income +
                ", width=" + width +
                ", height=" + height +
                '}'.toString()
    }
}
