package com.github.weewar.mapviewer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class WeewarMapViewer

fun main(args: Array<String>) {
    SpringApplication.run(WeewarMapViewer::class, *args)
}
