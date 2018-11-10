package com.github.weewar.mapviewer.controllers

import com.github.weewar.mapviewer.dao.WeewarMapDAO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.ModelAndView

@Controller
class MapPageController(private val weewarMapDAO: WeewarMapDAO) {

    @GetMapping("/map/{map_id}")
    fun mapPage(@PathVariable("map_id") mapId: Int): ModelAndView =
            weewarMapDAO.findMapHeaderById(mapId)
                    .map { ModelAndView("map-page", "map", it) }
                    .orElseGet { ModelAndView("redirect:/search") }
}
