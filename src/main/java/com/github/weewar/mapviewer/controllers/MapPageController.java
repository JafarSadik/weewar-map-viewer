package com.github.weewar.mapviewer.controllers;

import com.github.weewar.mapviewer.views.MapPageView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapPageController {
    @GetMapping({"/map/{map_id}", "/map/{map_id}/{map_name}"})
    public ModelAndView mapPage(@PathVariable("map_id") Integer mapId,
                                @PathVariable(value = "map_name", required = false) String urlEncodedMapName) {
        MapPageView mapPageView = new MapPageView();
        mapPageView.mapId = mapId;
        mapPageView.mapName = "Three ways";
        mapPageView.revision = 1;
        mapPageView.creator = "bert";
        mapPageView.players = 2;
        mapPageView.startCredits = 100;
        mapPageView.income = 200;
        mapPageView.width = 25;
        mapPageView.height = 20;
        return new ModelAndView("map-page", "map", mapPageView);
    }

    @GetMapping("/map")
    public String gotoSearchPage() {
        return "redirect:/search";
    }
}
