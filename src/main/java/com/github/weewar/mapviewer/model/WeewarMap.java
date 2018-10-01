package com.github.weewar.mapviewer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class WeewarMap {
    protected MapHeader header;
    protected MapTerrain terrain;

    public WeewarMap(MapHeader header, MapTerrain terrain) {
        this.header = header;
        this.terrain = terrain;
    }

    public Vector2D<Integer> getSize() {
        return header.getSize();
    }

    public Vector2D<Integer> hexToPixel(int column, int row) {
        return header.hexToPixel(column, row);
    }

    public Vector2D<Integer> getSizeInPixels() {
        return header.getSizeInPixels();
    }

    public MapHeader getHeader() {
        return header;
    }

    public MapTerrain getTerrain() {
        return terrain;
    }

    @Override
    public String toString() {
        return "WeewarMap{" +
                "header=" + header +
                ", terrain=" + terrain +
                '}';
    }

    public static class Mutable extends WeewarMap {
        public Mutable() {
            super(null, null);
        }

        @JsonUnwrapped
        public void setMapHeader(MapHeader mapHeader) {
            super.header = mapHeader;
        }

        @JsonProperty("terrain")
        public void setTerrain(MapTerrain terrain) {
            super.terrain = terrain;
        }

        public WeewarMap toImmutable() {
            return new WeewarMap(header, terrain);
        }
    }
}
