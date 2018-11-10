package com.github.weewar.mapviewer.controllers

import com.github.weewar.mapviewer.dao.MapSearchCriteria
import com.github.weewar.mapviewer.dao.WeewarMapDAO
import com.github.weewar.mapviewer.model.MapHeader
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class SearchPageController(private val weewarMapDAO: WeewarMapDAO) {

    @GetMapping("/search")
    fun searchPage(): String = "search-page"

    @ResponseBody
    @GetMapping("/api/maps", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMaps(@RequestParam("first", required = false, defaultValue = "0") first: Int,
                @RequestParam("count", required = false, defaultValue = "50") count: Int): List<MapHeader> {

        val searchCriteria = MapSearchCriteria(firstElement = first, pageSize = count)
        return weewarMapDAO.findMapHeaders(searchCriteria)
    }
}
