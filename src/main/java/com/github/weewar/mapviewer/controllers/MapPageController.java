package com.github.weewar.mapviewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MapPageController {
    @GetMapping({"/map/{map_id}", "/map/{map_id}/{map_name}"})
    public String mapPage(@PathVariable("map_id") Integer mapId,
                          @PathVariable(value = "map_name", required = false) String mapName,
                          Model model) {
        model.addAttribute("id", mapId);
        model.addAttribute("name", mapName);
        return "map-page";
    }

    @GetMapping("/map")
    public String gotoSearchPage() {
        return "redirect:/search";
    }
}
