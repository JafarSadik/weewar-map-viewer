package com.github.weewar.mapviewer.service.impl;

import com.github.weewar.mapviewer.exceptions.ImageNotFoundException;
import com.github.weewar.mapviewer.exceptions.ImagePreloadException;
import com.github.weewar.mapviewer.model.enums.Direction;
import com.github.weewar.mapviewer.model.enums.Owner;
import com.github.weewar.mapviewer.model.enums.TerrainType;
import com.github.weewar.mapviewer.model.enums.UnitType;
import com.github.weewar.mapviewer.service.ImageRepository;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

@Service
public class ImageRepositoryImpl implements ImageRepository {
    private Image plain;

    @Override
    public Image getTerrain(TerrainType terrainType, Owner owner, Direction direction) throws ImageNotFoundException {
        return plain;
    }

    @Override
    public Image getUnit(UnitType terrainType, Owner owner) throws ImageNotFoundException {
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
