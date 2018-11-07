package com.github.weewar.mapviewer.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.weewar.mapviewer.model.MapConstants.hexHeight
import com.github.weewar.mapviewer.model.MapConstants.hexHorizontalOffset
import com.github.weewar.mapviewer.model.MapConstants.hexVerticalDistance
import com.github.weewar.mapviewer.model.MapConstants.hexWidth
import java.lang.Math.round

class MapHeader @JsonCreator
constructor(@JsonProperty("id") val mapId: Int, @JsonProperty("name") val mapName: String, @JsonProperty("revision") val revision: Int,
            @JsonProperty("creator") val creator: String, @JsonProperty("players") val players: Int, @JsonProperty("width") val width: Int,
            @JsonProperty("perBaseCredits") val income: Int, @JsonProperty("initialCredits") val startCredits: Int,
            @JsonProperty("height") val height: Int) {

    @JsonIgnore
    fun size(): Pair<Int, Int> = Pair(width, height)

    @JsonIgnore
    fun sizeInPixels(): Pair<Int, Int> {
        val maxHorizontalOffset = if (height > 1) hexHorizontalOffset else 0F
        val x = round(width * hexWidth + maxHorizontalOffset)
        val y = round(hexHeight + (height - 1) * hexVerticalDistance)
        return Pair(x, y)
    }

    val urlEncodedMapName: String
        get() = "undefined"

    fun hexToPixel(column: Int, row: Int): Pair<Int, Int> {
        val rowHorizontalOffset = if (row % 2 != 0) hexHorizontalOffset else 0F
        val x = round(column * hexWidth + rowHorizontalOffset)
        val y = round(row * hexVerticalDistance)
        return Pair(x, y)
    }

    override fun toString(): String {
        return "MapHeader{" +
                "mapId=" + mapId +
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
