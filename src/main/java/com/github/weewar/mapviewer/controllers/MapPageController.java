package com.github.weewar.mapviewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapPageController {
    @GetMapping("/maps")
    public String mapPage(@RequestParam(value = "id") String mapId, Model model) {
        model.addAttribute("id", mapId);
        return "map-page";
    }
}
