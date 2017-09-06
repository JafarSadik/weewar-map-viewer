package com.github.weewar.mapviewer.model;

public interface HexMap {
    int HEX_WIDTH = 32; //pixels
    int HEX_HEIGHT = 34;//pixels

    Vector2D<Integer> hexToPixel(int column, int row);

    Vector2D<Integer> getSizeInPixels();

    Vector2D<Integer> getSize();
}
