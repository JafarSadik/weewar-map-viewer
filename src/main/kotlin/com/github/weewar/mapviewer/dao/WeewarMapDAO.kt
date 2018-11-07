package com.github.weewar.mapviewer.dao

import com.github.weewar.mapviewer.model.MapHeader
import com.github.weewar.mapviewer.model.WeewarMap
import java.util.*

interface WeewarMapDAO {
    fun findMapById(mapId: Int): Optional<WeewarMap>

    fun findMapHeaderById(mapId: Int): Optional<MapHeader>

    fun findMapHeaders(searchCriteria: MapSearchCriteria): List<MapHeader>
}
