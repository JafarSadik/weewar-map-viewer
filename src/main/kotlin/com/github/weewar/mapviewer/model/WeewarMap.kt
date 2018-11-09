package com.github.weewar.mapviewer.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonUnwrapped

class WeewarMap {
    @JsonUnwrapped
    lateinit var header: MapHeader

    @JsonProperty("terrain")
    lateinit var terrain: List<Tile>

    fun sizeInPixels() = header.sizeInPixels()

    fun hexToPixel(column: Int, row: Int) = header.hexToPixel(column, row)

    override fun toString(): String {
        return "WeewarMap(header=$header, terrain=$terrain)"
    }
}
