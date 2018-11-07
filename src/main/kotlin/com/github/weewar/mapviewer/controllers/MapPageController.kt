package com.github.weewar.mapviewer.controllers

import com.github.weewar.mapviewer.dao.WeewarMapDAO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.ModelAndView

@Controller
class MapPageController(private val weewarMapDAO: WeewarMapDAO) {

    @GetMapping("/map/{map_id}", "/map/{map_id}/{map_name}", "/map/{map_id}/{map_name}/{map_revision}")
    fun mapPage(@PathVariable("map_id") mapId: Int,
                @PathVariable(value = "map_revision", required = false) urlEncodedMapRevision: String?,
                @PathVariable(value = "map_name", required = false) urlEncodedMapName: String?): ModelAndView {

        return weewarMapDAO.findMapHeaderById(mapId)
                .map { ModelAndView("map-page", "map", it) }
                .orElseGet { ModelAndView("redirect:/search") }
    }
}
