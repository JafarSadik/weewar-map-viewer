package com.github.weewar.mapviewer.service.impl

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.weewar.mapviewer.model.*
import com.github.weewar.mapviewer.service.WeewarMapLoader
import com.github.weewar.mapviewer.utils.ClassPath
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.URL

@Service
class WeewarMapLoaderImpl(val classPath: ClassPath) : WeewarMapLoader {
    private val jsonMapper: ObjectMapper by lazy { configObjectMapper() }

    @Throws(MapParseException::class)
    override fun load(mapId: Int): WeewarMap {
        val path = mapsDir + mapId
        val mapURL = classPath.resource(path)
        return load(mapURL)
    }

    @Throws(MapParseException::class)
    override fun load(mapURL: URL): WeewarMap =
            try {
                jsonMapper.readValue(mapURL, WeewarMap::class.java)
            } catch (e: IOException) {
                throw MapParseException("Failed to parse json map file: " + mapURL.file, e)
            }

    @Throws(MapParseException::class)
    override fun loadAll(): List<WeewarMap> {
        return classPath.resources(mapsDir).map { load(it) }
    }

    private fun configObjectMapper() = ObjectMapper().registerModule(KotlinModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}
