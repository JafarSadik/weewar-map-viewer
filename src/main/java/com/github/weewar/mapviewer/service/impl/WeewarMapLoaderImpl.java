package com.github.weewar.mapviewer.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.weewar.mapviewer.exceptions.MapParseException;
import com.github.weewar.mapviewer.model.AppPaths;
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
    private final ObjectMapper jsonMapper;

    public WeewarMapLoaderImpl() {
        this.jsonMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public WeewarMap load(int mapId) throws MapParseException {
        String path = AppPaths.mapsDir + mapId;
        URL mapURL = ClassPath.resource(path);
        return load(mapURL);
    }

    @Override
    public WeewarMap load(URL url) throws MapParseException {
        try {
            return jsonMapper.readValue(url, WeewarMap.Mutable.class).immutable();
        } catch (IOException e) {
            throw new MapParseException("Failed to parse json map file: " + url.getFile(), e);
        }
    }

    @Override
    public List<WeewarMap> loadAll() throws MapParseException {
        return ClassPath.resources(AppPaths.mapsDir + "*")
                .stream()
                .map(this::load)
                .collect(toList());
    }
}
