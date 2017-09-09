package com.github.weewar.mapviewer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.weewar.mapviewer.model.enums.Direction;
import com.github.weewar.mapviewer.model.enums.Owner;
import com.github.weewar.mapviewer.model.enums.TerrainType;
import com.github.weewar.mapviewer.model.enums.UnitType;

public class Tile {
    private final int x, y;
    private final TerrainType type;
    private final Owner startFaction;
    private final Direction direction;
    private final UnitType unit;
    private final Owner unitOwner;

    @JsonCreator
    public Tile(@JsonProperty("x") int x, @JsonProperty("y") int y, @JsonProperty("type") TerrainType type,
                @JsonProperty("startFaction") Owner startFaction, @JsonProperty("direction") Direction direction,
                @JsonProperty("unit") UnitType unit, @JsonProperty("unitOwner") Owner unitOwner) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.startFaction = startFaction;
        this.direction = direction;
        this.unit = unit;
        this.unitOwner = unitOwner;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TerrainType getType() {
        return type;
    }

    public Owner getStartFaction() {
        return startFaction;
    }

    public Direction getDirection() {
        return direction;
    }

    public UnitType getUnit() {
        return unit;
    }

    public Owner getUnitOwner() {
        return unitOwner;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                ", startFaction=" + startFaction +
                ", direction=" + direction +
                ", unit=" + unit +
                ", unitOwner=" + unitOwner +
                '}';
    }
}
