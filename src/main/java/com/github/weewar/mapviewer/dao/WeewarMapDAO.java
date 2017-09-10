package com.github.weewar.mapviewer.dao;

import com.github.weewar.mapviewer.model.MapHeader;
import com.github.weewar.mapviewer.model.WeewarMap;

import java.util.List;
import java.util.Optional;

public interface WeewarMapDAO {
    Optional<WeewarMap> findMapById(int mapId);

    Optional<MapHeader> findMapHeaderById(int mapId);

    List<MapHeader> findMapHeaders(MapSearchCriteria searchCriteria);
}
