package com.github.weewar.mapviewer.controllers;

import com.github.weewar.mapviewer.dao.WeewarMapDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class MapPageController {
    private final WeewarMapDAO weewarMapDAO;

    @Autowired
    public MapPageController(WeewarMapDAO weewarMapDAO) {
        this.weewarMapDAO = weewarMapDAO;
    }

    @GetMapping({"/map/{map_id}", "/map/{map_id}/{map_name}", "/map/{map_id}/{map_name}/{map_revision}"})
    public ModelAndView mapPage(@PathVariable("map_id") Integer mapId,
                                @PathVariable(value = "map_revision", required = false) String urlEncodedMapRevision,
                                @PathVariable(value = "map_name", required = false) String urlEncodedMapName) {
        return weewarMapDAO.getMapHeaderById(mapId)
                .map(mapHeader -> new ModelAndView("map-page", "map", mapHeader))
                .orElseGet(() -> new ModelAndView("redirect:/search"));
    }

    @ResponseBody
    @GetMapping(value = {"/api/maps"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getMaps(@RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
                                 @RequestParam(value = "size", required = false, defaultValue = "50") Integer pageSize) {
        return Arrays.asList(1, 2, 3, 4, 5);
    }
}
