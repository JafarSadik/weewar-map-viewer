package com.github.weewar.mapviewer.service.impl.keys

import com.github.weewar.mapviewer.model.enums.Direction
import com.github.weewar.mapviewer.model.enums.Owner
import com.github.weewar.mapviewer.model.enums.TerrainType
import java.util.*

class TerrainImageKey(val terrainType: TerrainType, val owner: Owner?, val direction: Direction?) {

    override fun hashCode(): Int {
        return Objects.hash(owner, terrainType, direction)
    }

    override fun equals(obj: Any?): Boolean {
        if (obj == null || obj !is TerrainImageKey) {
            return false
        }

        val that = obj as TerrainImageKey?
        return this.owner == that!!.owner &&
                this.terrainType == that.terrainType &&
                this.direction == that.direction
    }

    override fun toString(): String {
        return "TerrainImageKey{" +
                "terrainType=" + terrainType +
                ", owner=" + owner +
                ", direction=" + direction +
                '}'.toString()
    }
}
