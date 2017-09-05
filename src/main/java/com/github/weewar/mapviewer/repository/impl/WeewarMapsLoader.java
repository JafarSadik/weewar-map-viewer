package com.github.weewar.mapviewer.repository.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.weewar.mapviewer.exceptions.MapLoadException;
import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.utils.ClassPath;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class WeewarMapsLoader {
    private final ObjectMapper objectMapper;

    public WeewarMapsLoader() {
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<WeewarMap> loadAll(String mapsLocation) throws MapLoadException {
        try {
            return ClassPath.resources(mapsLocation)
                    .parallelStream()
                    .map(this::loadMap)
                    .collect(toList());
        } catch (IOException e) {
            throw new MapLoadException("Unable to locate maps in a classpath: " + mapsLocation, e);
        }
    }

    private WeewarMap loadMap(Resource resource) throws MapLoadException {
        try {
            return objectMapper.readValue(resource.getURL(), WeewarMap.class);
        } catch (IOException e) {
            throw new MapLoadException("Failed to parse json map file: " + resource.getFilename(), e);
        }
    }
}
