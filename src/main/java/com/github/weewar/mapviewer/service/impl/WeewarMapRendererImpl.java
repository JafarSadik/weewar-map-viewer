package com.github.weewar.mapviewer.service.impl;

import com.github.weewar.mapviewer.exceptions.ImageNotFoundException;
import com.github.weewar.mapviewer.exceptions.ImageResizeException;
import com.github.weewar.mapviewer.model.Tile;
import com.github.weewar.mapviewer.model.Vector2D;
import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.service.ImageRepository;
import com.github.weewar.mapviewer.service.WeewarMapRenderer;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.AlphaInterpolation;
import net.coobird.thumbnailator.resizers.configurations.Antialiasing;
import net.coobird.thumbnailator.resizers.configurations.Dithering;
import net.coobird.thumbnailator.resizers.configurations.Rendering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class WeewarMapRendererImpl implements WeewarMapRenderer {
    private final ImageRepository images;

    @Autowired
    public WeewarMapRendererImpl(ImageRepository images) {
        this.images = images;
    }

    @Override
    public BufferedImage render(WeewarMap weewarMap) throws ImageNotFoundException {
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

    @Override
    public BufferedImage renderThumbnail(WeewarMap weewarMap, int width, int height) throws ImageNotFoundException, ImageResizeException {
        BufferedImage weewarMapImage = render(weewarMap);
        try {
            return Thumbnails.of(weewarMapImage)
                    .size(width, height)
                    .alphaInterpolation(AlphaInterpolation.QUALITY)
                    .antialiasing(Antialiasing.ON)
                    .dithering(Dithering.DEFAULT)
                    .rendering(Rendering.QUALITY)
                    .keepAspectRatio(true)
                    .outputQuality(1.0)
                    .asBufferedImage();
        } catch (IOException e) {
            throw new ImageResizeException(e);
        }
    }
}
