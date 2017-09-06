package com.github.weewar.mapviewer.controllers;

import com.github.weewar.mapviewer.dao.MapDAO;
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
    private final MapDAO mapDAO;

    @Autowired
    public ImageController(WeewarMapRenderer weewarMapRenderer, MapDAO mapDAO) {
        this.weewarMapRenderer = weewarMapRenderer;
        this.mapDAO = mapDAO;
    }

    @GetMapping(value = "/images/maps/{map_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> renderWeewarMap(@PathVariable("map_id") Integer mapId) {
        return mapDAO.findByMapId(mapId).map(weewarMap -> {
            BufferedImage image = weewarMapRenderer.render(weewarMap);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(Images.toPNG(image));

        }).orElse(ResponseEntity.notFound().build());
    }
}
