package com.github.weewar.mapviewer.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.weewar.mapviewer.exceptions.MapParseException;
import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.service.WeewarMapLoader;
import com.github.weewar.mapviewer.utils.ClassPath;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class WeewarMapLoaderImpl implements WeewarMapLoader {
    @Override
    public List<WeewarMap> loadAll(String mapsLocation) {
        return ClassPath.resources(mapsLocation)
                .parallelStream()
                .map(this::loadMap)
                .collect(toList());
    }

    private WeewarMap loadMap(Resource resource) throws MapParseException {
        try {
            ObjectMapper jsonMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return jsonMapper.readValue(resource.getURL(), WeewarMap.class);
        } catch (IOException e) {
            throw new MapParseException("Failed to parse json map file: " + resource.getFilename(), e);
        }
    }
}
