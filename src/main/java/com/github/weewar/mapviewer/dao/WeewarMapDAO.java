package com.github.weewar.mapviewer.dao;

import com.github.weewar.mapviewer.model.MapHeader;
import com.github.weewar.mapviewer.model.WeewarMap;

import java.util.Optional;

public interface WeewarMapDAO {
    Optional<WeewarMap> getMapById(int mapId);

    Optional<MapHeader> getMapHeaderById(int mapId);
}
