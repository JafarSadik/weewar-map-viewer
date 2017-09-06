package com.github.weewar.mapviewer.dao;

import com.github.weewar.mapviewer.model.WeewarMap;

import java.util.Optional;

public interface MapDAO {
    Optional<WeewarMap> findByMapId(long mapId);
}
