package com.github.weewar.mapviewer.controllers;

import com.github.weewar.mapviewer.dao.WeewarMapDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapPageController {
    private final WeewarMapDAO weewarMapDAO;

    @Autowired
    public MapPageController(WeewarMapDAO weewarMapDAO) {
        this.weewarMapDAO = weewarMapDAO;
    }

    @GetMapping({"/map/{map_id}", "/map/{map_id}/{map_name}", "/map/{map_id}/{map_name}/{map_revision}"})
    public ModelAndView mapPage(@PathVariable("map_id") Integer mapId,
                                @PathVariable(value = "map_name", required = false) String urlEncodedMapName,
                                @PathVariable(value = "map_revision", required = false) String urlEncodedMapRevision) {
        return weewarMapDAO.findByMapId(mapId)
                .map(map -> new ModelAndView("map-page", "map", map))
                .orElseGet(() -> new ModelAndView("redirect:/search"));
    }
}
