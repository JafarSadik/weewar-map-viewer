package com.github.weewar.mapviewer.service.impl;

import com.github.weewar.mapviewer.model.Tile;
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
        // create empty image of a proper size
        Vector2D<Integer> imageSize = weewarMap.getSizeInPixels();
        BufferedImage weewarMapImage = new BufferedImage(imageSize.getX(), imageSize.getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = weewarMapImage.getGraphics();

        // fill with a transparent background
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, imageSize.getX(), imageSize.getY());

        // render terrain, buildings and units
        for (Tile tile : weewarMap.getTerrain()) {
            Vector2D<Integer> imageLocation = weewarMap.hexToPixel(tile.getX(), tile.getY());
            // render terrain and buildings
            Image terrain = images.getTerrain(tile.getType(), tile.getStartFaction(), tile.getDirection());
            g.drawImage(terrain, imageLocation.getX(), imageLocation.getY(), null);

            // render units
            boolean unitExists = tile.getUnit() != null;
            if (unitExists) {
                Image unit = images.getUnit(tile.getUnit(), tile.getUnitOwner());
                g.drawImage(unit, imageLocation.getX(), imageLocation.getY(), null);
            }
        }
        return weewarMapImage;
    }
}
