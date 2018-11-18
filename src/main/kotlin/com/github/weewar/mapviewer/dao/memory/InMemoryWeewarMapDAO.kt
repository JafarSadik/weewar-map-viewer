package com.github.weewar.mapviewer.dao.memory

import com.github.weewar.mapviewer.dao.MapSearchCriteria
import com.github.weewar.mapviewer.dao.WeewarMapDAO
import com.github.weewar.mapviewer.model.*
import com.github.weewar.mapviewer.service.WeewarMapLoader
import com.github.weewar.mapviewer.utils.ClassPath
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.stream.Collectors.toMap

@Repository
class InMemoryWeewarMapDAO(val weewarMapLoader: WeewarMapLoader, val classPath: ClassPath) : WeewarMapDAO {

    private val logger = LoggerFactory.getLogger(InMemoryWeewarMapDAO::class.java)
    private val weewarMapHeaders = ConcurrentHashMap<Int, MapHeader>(50, 0.75f, 1)

    override fun findMapById(mapId: Int): Optional<WeewarMap> =
            try {
                val weewarMap = weewarMapLoader.load(mapId)
                Optional.of(weewarMap)
            } catch (e: Exception) {
                logger.info("Map not found: id = $mapId")
                Optional.empty()
            }

    override fun findMapHeaderById(mapId: Int): Optional<MapHeader> = Optional.ofNullable(weewarMapHeaders[mapId])

    override fun findMapHeaders(searchCriteria: MapSearchCriteria): List<MapHeader> =
            weewarMapHeaders.values.sortedBy { it.id }
                    .drop(searchCriteria.firstElement)
                    .take(searchCriteria.pageSize)

    fun populate() = weewarMapHeaders.putAll(
            classPath.resources(mapsDir).parallelStream()
                    .map { url -> weewarMapLoader.load(url).header }
                    .collect(toMap({ it.id }, { it }))
    )
}
