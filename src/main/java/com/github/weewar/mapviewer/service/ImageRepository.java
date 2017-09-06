package com.github.weewar.mapviewer.service;

import com.github.weewar.mapviewer.exceptions.ImagePreloadException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

@Service
public class ImageRepository {
    private Image plain;

    public Image getTerrainImage(String terrainType, String owner, String direction) {
        return plain;
    }

    public Image getUnitImage(String unitType, String owner) {
        return plain;
    }

    public void preloadImages() throws ImagePreloadException {
        try {
            plain = ImageIO.read(getClass().getResource("/terrain/plain.png"));
        } catch (IOException e) {
            throw new ImagePreloadException(e);
        }
    }
}
