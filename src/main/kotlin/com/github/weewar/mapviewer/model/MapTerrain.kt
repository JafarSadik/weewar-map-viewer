package com.github.weewar.mapviewer.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonUnwrapped
import java.util.function.Consumer

class MapTerrain @JsonCreator
constructor(@JsonUnwrapped val tiles: List<Tile>) : Iterable<Tile> {

    override fun iterator(): Iterator<Tile> {
        return tiles.iterator()
    }

    override fun forEach(action: Consumer<in Tile>) {
        tiles.forEach(action)
    }

    override fun toString(): String {
        return "MapTerrain{" + "tiles=" + tiles + '}'.toString()
    }
}
