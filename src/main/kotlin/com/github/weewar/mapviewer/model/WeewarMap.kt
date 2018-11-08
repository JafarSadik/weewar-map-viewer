package com.github.weewar.mapviewer.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonUnwrapped

class WeewarMap {
    @JsonUnwrapped
    lateinit var header: MapHeader

    @JsonProperty("terrain")
    lateinit var terrain: List<Tile>

    fun size(): Pair<Int, Int> = header.size()

    fun sizeInPixels(): Pair<Int, Int> = header.sizeInPixels()

    fun hexToPixel(column: Int, row: Int): Pair<Int, Int> {
        return header.hexToPixel(column, row)
    }

    override fun toString(): String {
        return "WeewarMap{" +
                "header=" + header +
                ", terrain=" + terrain +
                '}'.toString()
    }
}
