package com.github.weewar.mapviewer.service;

import com.github.weewar.mapviewer.exceptions.MapParseException;
import com.github.weewar.mapviewer.model.WeewarMap;

import java.util.List;

public interface WeewarMapLoader {
    WeewarMap load(int mapId) throws MapParseException;

    List<WeewarMap> loadAll() throws MapParseException;
}
