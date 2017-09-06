package com.github.weewar.mapviewer.repository.memory;

import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.repository.MapDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toMap;

@Repository
public class InMemoryMapDAO implements MapDAO {
    private final Map<Long, WeewarMap> weewarMaps = new ConcurrentHashMap<>(13000 /*max maps*/,
            0.75f /*default load factor*/, 1 /*single shard*/);
    private final WeewarMapsLoader weewarMapsLoader;

    @Autowired
    public InMemoryMapDAO(WeewarMapsLoader weewarMapsLoader) {
        this.weewarMapsLoader = weewarMapsLoader;
    }

    @Override
    public Optional<WeewarMap> findByMapId(long mapId) {
        WeewarMap map = weewarMaps.get(mapId);
        return map != null ? Optional.of(map) : Optional.empty();
    }

    public void populate() {
        List<WeewarMap> loadedMaps = weewarMapsLoader.loadAll("/public/api/maps/*");
        weewarMaps.putAll(loadedMaps.stream().collect(toMap(WeewarMap::getMapId, map -> map)));
    }
}
