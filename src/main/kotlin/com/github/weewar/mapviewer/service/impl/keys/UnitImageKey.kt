package com.github.weewar.mapviewer.service.impl.keys

import com.github.weewar.mapviewer.model.enums.Owner
import com.github.weewar.mapviewer.model.enums.UnitType

data class UnitImageKey(
        val unitType: UnitType,
        val owner: Owner
)