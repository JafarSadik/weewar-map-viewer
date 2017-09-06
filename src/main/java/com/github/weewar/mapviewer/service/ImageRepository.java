package com.github.weewar.mapviewer.service;

import com.github.weewar.mapviewer.exceptions.ImagePreloadException;

import java.awt.*;

public interface ImageRepository {
    Image getTerrainImage(String terrainType, String owner, String direction);

    Image getUnitImage(String unitType, String owner);

    void preloadImages() throws ImagePreloadException;
}
