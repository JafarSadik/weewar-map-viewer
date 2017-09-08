package com.github.weewar.mapviewer.service.impl.keys;

import com.github.weewar.mapviewer.model.enums.Direction;
import com.github.weewar.mapviewer.model.enums.Owner;
import com.github.weewar.mapviewer.model.enums.TerrainType;

import java.util.Objects;

public final class TerrainImageKey {
    private final TerrainType terrainType;
    private final Owner owner;
    private final Direction direction;

    public TerrainImageKey(TerrainType terrainType, Owner owner, Direction direction) {
        this.terrainType = terrainType;
        this.owner = owner;
        this.direction = direction;
    }

    public Owner getOwner() {
        return owner;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, terrainType, direction);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TerrainImageKey)) {
            return false;
        }

        TerrainImageKey that = (TerrainImageKey) obj;
        return Objects.equals(this.owner, that.owner) &&
                Objects.equals(this.terrainType, that.terrainType) &&
                Objects.equals(this.direction, that.direction);
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
