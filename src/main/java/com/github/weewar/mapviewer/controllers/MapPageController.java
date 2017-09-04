package com.github.weewar.mapviewer.controllers;

import com.github.weewar.mapviewer.repository.MapDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapPageController {
    private final MapDAO mapDAO;

    @Autowired
    public MapPageController(MapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    @GetMapping({"/map/{map_id}", "/map/{map_id}/{map_name}"})
    public ModelAndView mapPage(@PathVariable("map_id") Integer mapId,
                                @PathVariable(value = "map_name", required = false) String urlEncodedMapName) {
        return mapDAO.findByMapId(mapId)
                .map(map -> new ModelAndView("map-page", "map", map))
                .orElseGet(this::gotoSearchPage);
    }

    @GetMapping("/map")
    public ModelAndView gotoSearchPage() {
        return new ModelAndView("redirect:/search");
    }
}
