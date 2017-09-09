package com.github.weewar.mapviewer.dao.memory;

import com.github.weewar.mapviewer.dao.WeewarMapDAO;
import com.github.weewar.mapviewer.model.WeewarMap;
import com.github.weewar.mapviewer.service.WeewarMapLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryWeewarMapDAO implements WeewarMapDAO {
    private final Logger logger = LoggerFactory.getLogger(InMemoryWeewarMapDAO.class);
    private final WeewarMapLoader weewarMapLoader;

    @Autowired
    public InMemoryWeewarMapDAO(WeewarMapLoader weewarMapLoader) {
        this.weewarMapLoader = weewarMapLoader;
    }

    @Override
    public Optional<WeewarMap> findByMapId(int mapId) {
        try {
            WeewarMap weewarMap = weewarMapLoader.load(mapId);
            return Optional.of(weewarMap);
        } catch (Exception e) {
            logger.info("Map not found: id = " + mapId);
            return Optional.empty();
        }
    }

    public void populate() {
        //List<WeewarMap> loadedMaps = weewarMapLoader.loadAll("/public/api/maps/*");
        //weewarMaps.putAll(loadedMaps.stream().collect(toMap(WeewarMap::getMapId, map -> map)));
    }
}
