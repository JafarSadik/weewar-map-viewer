package com.github.weewar.mapviewer.startup

import com.github.weewar.mapviewer.service.ImageRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class PreloadImages(private val imageRepository: ImageRepository) {
    private val logger = LoggerFactory.getLogger(PreloadImages::class.java)

    @PostConstruct
    fun onStartup() {
        logger.info("Preloading weewar images.")
        imageRepository.preloadImages()
    }
}
