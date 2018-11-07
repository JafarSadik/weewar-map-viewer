package com.github.weewar.mapviewer.service.impl.keys

import com.github.weewar.mapviewer.model.enums.Owner
import com.github.weewar.mapviewer.model.enums.UnitType
import java.util.*

class UnitImageKey(val unitType: UnitType, val owner: Owner) {

    override fun hashCode(): Int {
        return Objects.hash(owner, unitType)
    }

    override fun equals(obj: Any?): Boolean {
        if (obj == null || obj !is UnitImageKey) {
            return false
        }

        val that = obj as UnitImageKey?
        return this.owner == that!!.owner && this.unitType == that.unitType
    }

    override fun toString(): String {
        return "UnitImageKey{" +
                "unitType=" + unitType +
                ", owner=" + owner +
                '}'.toString()
    }
}
