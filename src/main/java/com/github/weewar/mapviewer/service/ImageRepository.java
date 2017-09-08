package com.github.weewar.mapviewer.service;

import com.github.weewar.mapviewer.exceptions.ImagePreloadException;
import com.github.weewar.mapviewer.model.enums.Direction;
import com.github.weewar.mapviewer.model.enums.Owner;
import com.github.weewar.mapviewer.model.enums.TerrainType;
import com.github.weewar.mapviewer.model.enums.UnitType;

import java.awt.*;

public interface ImageRepository {
    Image getTerrain(TerrainType terrainType, Owner owner, Direction direction);

    Image getUnit(UnitType terrainType, Owner owner);

    void preloadImages() throws ImagePreloadException;
}
