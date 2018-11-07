package com.github.weewar.mapviewer.service

import com.github.weewar.mapviewer.exceptions.MapParseException
import com.github.weewar.mapviewer.model.WeewarMap
import java.net.URL

interface WeewarMapLoader {
    @Throws(MapParseException::class)
    fun load(mapId: Int): WeewarMap

    @Throws(MapParseException::class)
    fun load(mapURL: URL): WeewarMap

    @Throws(MapParseException::class)
    fun loadAll(): List<WeewarMap>
}
