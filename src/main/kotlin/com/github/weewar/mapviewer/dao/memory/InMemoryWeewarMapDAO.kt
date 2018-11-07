package com.github.weewar.mapviewer.dao.memory

import com.github.weewar.mapviewer.dao.MapSearchCriteria
import com.github.weewar.mapviewer.dao.WeewarMapDAO
import com.github.weewar.mapviewer.model.AppPaths
import com.github.weewar.mapviewer.model.MapHeader
import com.github.weewar.mapviewer.model.WeewarMap
import com.github.weewar.mapviewer.service.WeewarMapLoader
import com.github.weewar.mapviewer.utils.ClassPath
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Collectors.toMap

@Repository
class InMemoryWeewarMapDAO(private val weewarMapLoader: WeewarMapLoader) : WeewarMapDAO {

    private val logger = LoggerFactory.getLogger(InMemoryWeewarMapDAO::class.java)
    private val weewarMapHeaders = ConcurrentHashMap<Int, MapHeader>(1300 /*max amount of maps*/,
            0.75f /*default load factor*/, 1 /*single shard*/)

    override fun findMapById(mapId: Int): Optional<WeewarMap> =
            try {
                val weewarMap = weewarMapLoader.load(mapId)
                Optional.of(weewarMap)
            } catch (e: Exception) {
                logger.info("Map not found: id = $mapId")
                Optional.empty()
            }

    override fun findMapHeaderById(mapId: Int): Optional<MapHeader> {
        val mapHeader = weewarMapHeaders[mapId]
        return if (mapHeader != null) Optional.of(mapHeader) else Optional.empty()
    }

    override fun findMapHeaders(searchCriteria: MapSearchCriteria): List<MapHeader> {
        return weewarMapHeaders.values.sortedBy { it.mapId }
                .drop(searchCriteria.getFirstElement())
                .take(searchCriteria.getPageSize())
    }

    fun populate() {
        weewarMapHeaders.putAll(
                ClassPath.resources(AppPaths.mapsDir + "*")
                        .parallelStream()
                        .map { url -> weewarMapLoader.load(url).header }
                        .collect(toMap({ it.mapId }, { it }))
        )
    }
}
