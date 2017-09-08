package com.github.weewar.mapviewer.dao;

import com.github.weewar.mapviewer.model.WeewarMap;

import java.util.Optional;

public interface WeewarMapDAO {
    Optional<WeewarMap> findByMapId(int mapId);
}
