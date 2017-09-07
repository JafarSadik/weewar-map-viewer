package com.github.weewar.mapviewer.service.impl;

import com.github.weewar.mapviewer.model.Vector2D;
import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.service.ImageRepository;
import com.github.weewar.mapviewer.service.WeewarMapRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class WeewarMapRendererImpl implements WeewarMapRenderer {
    private final ImageRepository images;

    @Autowired
    public WeewarMapRendererImpl(ImageRepository images) {
        this.images = images;
    }

    @Override
    public BufferedImage render(WeewarMap weewarMap) {
        Vector2D<Integer> imageSize = weewarMap.getSizeInPixels();
        BufferedImage weewarMapImage = new BufferedImage(imageSize.getX(), imageSize.getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = weewarMapImage.getGraphics();

        // fill with transparent background
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, imageSize.getX(), imageSize.getY());

        // render all tiles and units
        for (int column = 0; column < weewarMap.getWidth(); column++) {
            for (int row = 0; row < weewarMap.getHeight(); row++) {
                Vector2D<Integer> point = weewarMap.hexToPixel(column, row);
                Image plain = images.getTerrainImage(null, null, null);
                g.drawImage(plain, point.getX(), point.getY(), null);
            }
        }
        return weewarMapImage;
    }
}
