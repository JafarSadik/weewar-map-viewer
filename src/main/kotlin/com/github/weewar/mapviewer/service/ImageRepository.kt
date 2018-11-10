package com.github.weewar.mapviewer.service

import com.github.weewar.mapviewer.model.ImageNotFoundException
import com.github.weewar.mapviewer.model.ImagePreloadException
import com.github.weewar.mapviewer.model.enums.*
import java.awt.Image

interface ImageRepository {
    @Throws(ImageNotFoundException::class)
    fun getTerrain(terrainType: TerrainType, owner: Owner?, direction: Direction?): Image

    @Throws(ImageNotFoundException::class)
    fun getUnit(unitType: UnitType, owner: Owner): Image

    @Throws(ImagePreloadException::class)
    fun preloadImages()
}

