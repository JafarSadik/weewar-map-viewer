package com.github.weewar.mapviewer.service.impl

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.weewar.mapviewer.exceptions.MapParseException
import com.github.weewar.mapviewer.model.AppPaths
import com.github.weewar.mapviewer.model.WeewarMap
import com.github.weewar.mapviewer.service.WeewarMapLoader
import com.github.weewar.mapviewer.utils.ClassPath
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.URL

@Service
class WeewarMapLoaderImpl : WeewarMapLoader {
    private val jsonMapper: ObjectMapper

    init {
        this.jsonMapper = ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    @Throws(MapParseException::class)
    override fun load(mapId: Int): WeewarMap {
        val path = AppPaths.mapsDir + mapId
        val mapURL = ClassPath.resource(path)
        return load(mapURL)
    }

    @Throws(MapParseException::class)
    override fun load(mapURL: URL): WeewarMap {
        try {
            return jsonMapper.readValue(mapURL, WeewarMap::class.java)
        } catch (e: IOException) {
            throw MapParseException("Failed to parse json map file: " + mapURL.file, e)
        }

    }

    @Throws(MapParseException::class)
    override fun loadAll(): List<WeewarMap> {
        return ClassPath.resources(AppPaths.mapsDir + "*").map { load(it) }
    }
}
