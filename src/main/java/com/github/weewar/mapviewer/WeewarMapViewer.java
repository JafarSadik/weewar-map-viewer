package com.github.weewar.mapviewer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class WeewarMapViewer {
    @RequestMapping("/")
    @ResponseBody String hello() {
        return "Hello world! :D";
    }

    public static void main(String[] args) {
        SpringApplication.run(WeewarMapViewer.class, args);
    }
}
