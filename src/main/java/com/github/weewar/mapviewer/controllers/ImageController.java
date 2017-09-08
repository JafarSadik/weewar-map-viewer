package com.github.weewar.mapviewer.controllers;

import com.github.weewar.mapviewer.dao.WeewarMapDAO;
import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.service.WeewarMapRenderer;
import com.github.weewar.mapviewer.utils.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.image.BufferedImage;

@Controller
public class ImageController {
    private final WeewarMapRenderer weewarMapRenderer;
    private final WeewarMapDAO weewarMapDAO;

    @Autowired
    public ImageController(WeewarMapRenderer weewarMapRenderer, WeewarMapDAO weewarMapDAO) {
        this.weewarMapRenderer = weewarMapRenderer;
        this.weewarMapDAO = weewarMapDAO;
    }

    @GetMapping(value = "/images/maps/{map_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> renderWeewarMap(@PathVariable("map_id") Integer mapId) {
        return weewarMapDAO.findByMapId(mapId)
                .map(this::weewarMapPNGImage)
                .orElse(notFoundError());
    }

    private ResponseEntity<byte[]> weewarMapPNGImage(WeewarMap weewarMap) {
        BufferedImage image = weewarMapRenderer.render(weewarMap);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(Images.toPNG(image));
    }

    private ResponseEntity<byte[]> notFoundError() {
        return ResponseEntity.notFound().build();
    }
}
