package com.github.weewar.mapviewer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Terrain implements Iterable<Tile> {
    private final List<Tile> tiles;

    @JsonCreator
    public Terrain(@JsonUnwrapped List<Tile> tiles) {
        this.tiles = tiles;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    @Override
    public Iterator<Tile> iterator() {
        return tiles.iterator();
    }

    @Override
    public void forEach(Consumer<? super Tile> action) {
        tiles.forEach(action);
    }

    @Override
    public Spliterator<Tile> spliterator() {
        return null;
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
