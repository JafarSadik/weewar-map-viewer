package com.github.weewar.mapviewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String gotoSearchPage() {
        return "redirect:/search";
    }
}
