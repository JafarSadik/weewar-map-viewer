package com.github.weewar.mapviewer.service;

import com.github.weewar.mapviewer.model.WeewarMap;

import java.util.List;

public interface WeewarMapLoader {
    List<WeewarMap> loadAll(String mapsLocation);
}
