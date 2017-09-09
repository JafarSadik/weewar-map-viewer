package com.github.weewar.mapviewer.dao.memory;

import com.github.weewar.mapviewer.dao.WeewarMapDAO;
import com.github.weewar.mapviewer.model.AppPaths;
import com.github.weewar.mapviewer.model.MapHeader;
import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.service.WeewarMapLoader;
import com.github.weewar.mapviewer.utils.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryWeewarMapDAO implements WeewarMapDAO {
    private final Logger logger = LoggerFactory.getLogger(InMemoryWeewarMapDAO.class);
    private final Map<Integer, MapHeader> weewarMapHeaders = new ConcurrentHashMap<>(1300 /*max amount of maps*/,
            0.75f /*default load factor*/, 1 /*single shard*/);
    private final WeewarMapLoader weewarMapLoader;

    @Autowired
    public InMemoryWeewarMapDAO(WeewarMapLoader weewarMapLoader) {
        this.weewarMapLoader = weewarMapLoader;
    }

    @Override
    public Optional<WeewarMap> getMapById(int mapId) {
        try {
            WeewarMap weewarMap = weewarMapLoader.load(mapId);
            return Optional.of(weewarMap);
        } catch (Exception e) {
            logger.info("Map not found: id = " + mapId);
            return Optional.empty();
        }
    }

    @Override
    public Optional<MapHeader> getMapHeaderById(int mapId) {
        MapHeader mapHeader = weewarMapHeaders.get(mapId);
        return mapHeader != null ? Optional.of(mapHeader) : Optional.empty();
    }

    public void populate() {
        Map<Integer, MapHeader> loadedMapHeaders = ClassPath.resources(AppPaths.mapsDir + "*")
                .parallelStream()
                .map(url -> weewarMapLoader.load(url).getHeader())
                .collect(Collectors.toMap(MapHeader::getMapId, mapHeader -> mapHeader));
        weewarMapHeaders.putAll(loadedMapHeaders);
    }
}
