package com.github.weewar.mapviewer.service.impl.keys

import com.github.weewar.mapviewer.model.enums.Direction
import com.github.weewar.mapviewer.model.enums.Owner
import com.github.weewar.mapviewer.model.enums.TerrainType

data class TerrainImageKey(
        val terrainType: TerrainType,
        val owner: Owner?,
        val direction: Direction?
)