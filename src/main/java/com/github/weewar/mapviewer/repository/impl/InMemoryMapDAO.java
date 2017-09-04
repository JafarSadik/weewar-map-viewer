package com.github.weewar.mapviewer.repository.impl;

import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.repository.MapDAO;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryMapDAO implements MapDAO {
    private final Map<Long, WeewarMap> maps = new ConcurrentHashMap<>();

    @Override
    public Optional<WeewarMap> findByMapId(long mapId) {
        WeewarMap map = maps.get(mapId);
        return map != null ? Optional.of(map) : Optional.empty();
    }

    public void populate() throws IOException {
        maps.put(1L, new WeewarMap(1L, "Three ways", 0, "bert", 2,
                100, 200, 25, 200));
    }
}

