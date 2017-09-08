package com.github.weewar.mapviewer.service.impl;

import com.github.weewar.mapviewer.exceptions.ImageNotFoundException;
import com.github.weewar.mapviewer.exceptions.ImagePreloadException;
import com.github.weewar.mapviewer.model.enums.Direction;
import com.github.weewar.mapviewer.model.enums.Owner;
import com.github.weewar.mapviewer.model.enums.TerrainType;
import com.github.weewar.mapviewer.model.enums.UnitType;
import com.github.weewar.mapviewer.service.ImageRepository;
import com.github.weewar.mapviewer.service.impl.keys.TerrainImageKey;
import com.github.weewar.mapviewer.service.impl.keys.UnitImageKey;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ImageRepositoryImpl implements ImageRepository {
    private final Map<TerrainImageKey, Image> terrainImages = new ConcurrentHashMap<>(50 /*max terrain images*/,
            0.75f /*default load factor*/, 1 /*single shard*/);
    private final Map<UnitImageKey, Image> unitImages = new ConcurrentHashMap<>(200 /*max unit images*/,
            0.75f /*default load factor*/, 1 /*single shard*/);

    @Override
    public Image getTerrain(TerrainType terrainType, Owner owner, Direction direction) throws ImageNotFoundException {
        Image image = terrainImages.get(new TerrainImageKey(terrainType, owner, direction));
        if (image == null) {
            throw new ImageNotFoundException("Terrain image not found: type = " + terrainType + ", owner = " + owner +
                    ", direction = " + direction);
        }
        return image;
    }

    @Override
    public Image getUnit(UnitType unitType, Owner owner) throws ImageNotFoundException {
        Image image = unitImages.get(new UnitImageKey(unitType, owner));
        if (image == null) {
            throw new ImageNotFoundException("Unit image not found: type = " + unitType + ", owner = " + owner);
        }
        return image;
    }

    @Override
    public void preloadImages() throws ImagePreloadException {
        try {
            Yaml yaml = new Yaml();
            preloadTerrainImages(yaml);
            preloadUnitImages(yaml);
        } catch (IOException e) {
            throw new ImagePreloadException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private void preloadTerrainImages(Yaml yaml) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/.terrain.yml")) {
            Map<String, Map<String, String>> terrainFiles = yaml.loadAs(inputStream, Map.class);
            for (String terrainFile : terrainFiles.keySet()) {
                Map<String, String> terrainDetails = terrainFiles.get(terrainFile);
                TerrainType terrainType = TerrainType.of(terrainDetails.get("type"));
                Owner owner = Owner.of(terrainDetails.get("owner"));
                Direction direction = Direction.of(terrainDetails.get("direction"));
                URL url = getClass().getResource(terrainFile);
                Image terrainImage = ImageIO.read(url);
                terrainImages.put(new TerrainImageKey(terrainType, owner, direction), terrainImage);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void preloadUnitImages(Yaml yaml) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/.units.yml")) {
            Map<String, Map<String, String>> unitFiles = yaml.loadAs(inputStream, Map.class);
            for (String unitFile : unitFiles.keySet()) {
                Map<String, String> unitDetails = unitFiles.get(unitFile);
                UnitType unitType = UnitType.of(unitDetails.get("type"));
                Owner owner = Owner.of(unitDetails.get("owner"));
                URL url = getClass().getResource(unitFile);
                Image unitImage = ImageIO.read(url);
                unitImages.put(new UnitImageKey(unitType, owner), unitImage);
            }
        }
    }
}
