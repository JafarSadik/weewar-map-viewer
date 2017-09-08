package com.github.weewar.mapviewer.service;

import com.github.weewar.mapviewer.exceptions.ImageNotFoundException;
import com.github.weewar.mapviewer.exceptions.ImagePreloadException;
import com.github.weewar.mapviewer.model.enums.Direction;
import com.github.weewar.mapviewer.model.enums.Owner;
import com.github.weewar.mapviewer.model.enums.TerrainType;
import com.github.weewar.mapviewer.model.enums.UnitType;

import java.awt.*;

public interface ImageRepository {
    Image getTerrain(TerrainType terrainType, Owner owner, Direction direction) throws ImageNotFoundException;

    Image getUnit(UnitType terrainType, Owner owner) throws ImageNotFoundException;

    void preloadImages() throws ImagePreloadException;
}
