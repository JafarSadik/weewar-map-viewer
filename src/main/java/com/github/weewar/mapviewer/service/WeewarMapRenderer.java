package com.github.weewar.mapviewer.service;

import com.github.weewar.mapviewer.model.Vector2D;
import com.github.weewar.mapviewer.model.WeewarMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class WeewarMapRenderer {
    private final ImageRepository images;

    @Autowired
    public WeewarMapRenderer(ImageRepository images) {
        this.images = images;
    }

    public BufferedImage render(WeewarMap weewarMap) {
        Vector2D<Integer> mapImageSize = weewarMap.getSizeInPixels();
        BufferedImage weewarMapImage = new BufferedImage(mapImageSize.getX(), mapImageSize.getY(), BufferedImage.TYPE_INT_RGB);
        Graphics g = weewarMapImage.getGraphics();

        // white map background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, mapImageSize.getX(), mapImageSize.getY());

        // render all tiles and units
        for (int column = 0; column < weewarMap.getWidth(); column++) {
            for (int row = 0; row < weewarMap.getHeight(); row++) {
                Vector2D point = weewarMap.hexToPixel(column, row);
                Image plain = images.getTerrainImage(null, null, null);
                g.drawImage(plain, (int) point.getX(), (int) point.getY(), null);
            }
        }
        return weewarMapImage;
    }
}
