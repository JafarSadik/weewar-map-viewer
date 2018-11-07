package com.github.weewar.mapviewer.service

import com.github.weewar.mapviewer.exceptions.ImageNotFoundException
import com.github.weewar.mapviewer.exceptions.ImagePreloadException
import com.github.weewar.mapviewer.model.enums.Direction
import com.github.weewar.mapviewer.model.enums.Owner
import com.github.weewar.mapviewer.model.enums.TerrainType
import com.github.weewar.mapviewer.model.enums.UnitType
import java.awt.Image

interface ImageRepository {
    @Throws(ImageNotFoundException::class)
    fun getTerrain(terrainType: TerrainType, owner: Owner?, direction: Direction?): Image

    @Throws(ImageNotFoundException::class)
    fun getUnit(unitType: UnitType, owner: Owner): Image

    @Throws(ImagePreloadException::class)
    fun preloadImages()
}
