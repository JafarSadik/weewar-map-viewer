package com.github.weewar.mapviewer.service.impl;

import com.github.weewar.mapviewer.exceptions.ImagePreloadException;
import com.github.weewar.mapviewer.service.ImageRepository;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

@Service
public class ImageRepositoryImpl implements ImageRepository {
    private Image plain;

    @Override
    public Image getTerrainImage(String terrainType, String owner, String direction) {
        return plain;
    }

    @Override
    public Image getUnitImage(String unitType, String owner) {
        return plain;
    }

    @Override
    public void preloadImages() throws ImagePreloadException {
        try {
            plain = ImageIO.read(getClass().getResource("/terrain/plain.png"));
        } catch (IOException e) {
            throw new ImagePreloadException(e);
        }
    }
}
