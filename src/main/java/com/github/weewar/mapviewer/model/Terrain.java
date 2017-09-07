package com.github.weewar.mapviewer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public class Terrain {
    public final List<Tile> tiles;

    @JsonCreator
    public Terrain(@JsonUnwrapped List<Tile> tiles) {
        this.tiles = tiles;
    }

    @Override
    public String toString() {
        return "Terrain{" + "tiles=" + tiles + '}';
    }
}
