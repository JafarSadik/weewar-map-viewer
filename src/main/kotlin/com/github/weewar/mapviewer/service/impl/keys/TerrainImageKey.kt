package com.github.weewar.mapviewer.service.impl.keys

import com.github.weewar.mapviewer.model.enums.*

data class TerrainImageKey(
        val terrainType: TerrainType,
        val owner: Owner?,
        val direction: Direction?
)