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

import static com.github.weewar.mapviewer.model.MapConstants.thumbnailHeight;
import static com.github.weewar.mapviewer.model.MapConstants.thumbnailWidth;

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
        return weewarMapDAO.getMapById(mapId)
                .map(this::weewarMapPNGImage)
                .orElse(notFoundError());
    }

    @GetMapping(value = "/images/maps/thumbnails/{map_id}")
    public ResponseEntity<byte[]> renderMapThumbnail(@PathVariable("map_id") Integer mapId) {
        return weewarMapDAO.getMapById(mapId)
                .map(this::weewarMapPNGThumbnail)
                .orElse(notFoundError());
    }

    private ResponseEntity<byte[]> weewarMapPNGImage(WeewarMap weewarMap) {
        BufferedImage image = weewarMapRenderer.render(weewarMap);
        byte[] pngData = Images.toPNG(image);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(pngData.length)
                .body(pngData);
    }

    private ResponseEntity<byte[]> weewarMapPNGThumbnail(WeewarMap weewarMap) {
        BufferedImage thumbnail = weewarMapRenderer.renderThumbnail(weewarMap, thumbnailWidth, thumbnailHeight);
        byte[] pngData = Images.toPNG(thumbnail);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(pngData.length)
                .body(pngData);
    }

    private ResponseEntity<byte[]> notFoundError() {
        return ResponseEntity.notFound().build();
    }
}
