package com.github.weewar.mapviewer.service.impl.keys;

import com.github.weewar.mapviewer.model.enums.Owner;
import com.github.weewar.mapviewer.model.enums.UnitType;

import java.util.Objects;

public final class UnitImageKey {
    private final UnitType unitType;
    private final Owner owner;

    public UnitImageKey(UnitType unitType, Owner owner) {
        this.unitType = unitType;
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, unitType);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof UnitImageKey)) {
            return false;
        }

        UnitImageKey that = (UnitImageKey) obj;
        return Objects.equals(this.owner, that.owner) &&
                Objects.equals(this.unitType, that.unitType);
    }
}
