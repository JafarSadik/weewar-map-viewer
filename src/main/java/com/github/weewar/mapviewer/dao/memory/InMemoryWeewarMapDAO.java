package com.github.weewar.mapviewer.dao.memory;

import com.github.weewar.mapviewer.dao.WeewarMapDAO;
import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.service.WeewarMapLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toMap;

@Repository
public class InMemoryWeewarMapDAO implements WeewarMapDAO {
    private final Map<Long, WeewarMap> weewarMaps = new ConcurrentHashMap<>(13000 /*max maps*/,
            0.75f /*default load factor*/, 1 /*single shard*/);
    private final WeewarMapLoader weewarMapLoader;

    @Autowired
    public InMemoryWeewarMapDAO(WeewarMapLoader weewarMapLoader) {
        this.weewarMapLoader = weewarMapLoader;
    }

    @Override
    public Optional<WeewarMap> findByMapId(long mapId) {
        WeewarMap map = weewarMaps.get(mapId);
        return map != null ? Optional.of(map) : Optional.empty();
    }

    public void populate() {
        List<WeewarMap> loadedMaps = weewarMapLoader.loadAll("/public/api/maps/*");
        weewarMaps.putAll(loadedMaps.stream().collect(toMap(WeewarMap::getMapId, map -> map)));
    }
}
