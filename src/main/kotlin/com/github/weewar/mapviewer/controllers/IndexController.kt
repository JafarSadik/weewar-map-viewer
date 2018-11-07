package com.github.weewar.mapviewer.controllers

import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController : ErrorController {

    @GetMapping("/")
    fun gotoSearchPage(): String {
        return "redirect:/search"
    }

    @GetMapping("/error")
    fun handleErrors(): String {
        return "redirect:/search"
    }

    override fun getErrorPath(): String {
        return "/error"
    }
}
