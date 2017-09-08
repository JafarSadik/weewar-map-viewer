package com.github.weewar.mapviewer.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.weewar.mapviewer.exceptions.MapParseException;
import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.service.WeewarMapLoader;
import com.github.weewar.mapviewer.utils.ClassPath;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class WeewarMapLoaderImpl implements WeewarMapLoader {
    private final String mapsDir = "/public/api/maps/";
    private final ObjectMapper jsonMapper;

    public WeewarMapLoaderImpl() {
        this.jsonMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public WeewarMap load(int mapId) throws MapParseException {
        String path = mapsDir + mapId;
        URL mapURL = ClassPath.resource(path);
        return loadMap(mapURL);
    }

    @Override
    public List<WeewarMap> loadAll() throws MapParseException {
        String locationPattern = mapsDir + "*";
        return ClassPath.resources(locationPattern)
                .stream()
                .map(this::loadMap)
                .collect(toList());
    }

    private WeewarMap loadMap(URL url) throws MapParseException {
        try {
            return jsonMapper.readValue(url, WeewarMap.class);
        } catch (IOException e) {
            throw new MapParseException("Failed to parse json map file: " + url.getFile(), e);
        }
    }
}
