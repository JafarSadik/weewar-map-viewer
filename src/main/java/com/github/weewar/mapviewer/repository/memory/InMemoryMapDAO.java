package com.github.weewar.mapviewer.repository.memory;

import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.repository.MapDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toMap;

@Repository
public class InMemoryMapDAO implements MapDAO {
    private final Map<Long, WeewarMap> maps = new ConcurrentHashMap<>();
    private final WeewarMapsLoader weewarMapsLoader;

    @Autowired
    public InMemoryMapDAO(WeewarMapsLoader weewarMapsLoader) {
        this.weewarMapsLoader = weewarMapsLoader;
    }

    @Override
    public Optional<WeewarMap> findByMapId(long mapId) {
        WeewarMap map = maps.get(mapId);
        return map != null ? Optional.of(map) : Optional.empty();
    }

    public void populate() {
        maps.putAll(weewarMapsLoader.loadAll("/public/api/maps/*").stream()
                .collect(toMap(WeewarMap::getMapId, map -> map)));
    }
}
