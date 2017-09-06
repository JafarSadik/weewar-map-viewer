package com.github.weewar.mapviewer.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController implements ErrorController {
    @GetMapping("/")
    public String gotoSearchPage() {
        return "redirect:/search";
    }

    @GetMapping("/error")
    public String handleErrors() {
        return "redirect:/search";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
