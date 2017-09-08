package com.github.weewar.mapviewer.startup;

import com.github.weewar.mapviewer.service.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PreloadImages {
    private final Logger logger = LoggerFactory.getLogger(PreloadImages.class);
    private final ImageRepository imageRepository;

    @Autowired
    public PreloadImages(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostConstruct
    public void onStartup() {
        logger.info("Preloading weewar images.");
        imageRepository.preloadImages();
    }
}
