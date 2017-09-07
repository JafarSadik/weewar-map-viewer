package com.github.weewar.mapviewer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.github.weewar.mapviewer.model.Constants.*;

public class WeewarMap {
    private final long mapId;
    private final String mapName;
    private final int revision;
    private final String creator;
    private final int players;
    private final int startCredits;
    private final int income;
    private final int width;
    private final int height;

    @JsonCreator
    public WeewarMap(@JsonProperty("id") long mapId, @JsonProperty("name") String mapName, @JsonProperty("revision") int revision,
                     @JsonProperty("creator") String creator, @JsonProperty("players") int players, @JsonProperty("initialCredits") int startCredits,
                     @JsonProperty("perBaseCredits") int income, @JsonProperty("width") int width, @JsonProperty("height") int height) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.revision = revision;
        this.creator = creator;
        this.players = players;
        this.startCredits = startCredits;
        this.income = income;
        this.width = width;
        this.height = height;
    }

    public Vector2D<Integer> getSize() {
        return new Vector2D<>(width, height);
    }

    public Vector2D<Integer> hexToPixel(int column, int row) {
        Vector2D<Integer> point = new Vector2D<>();
        float currentRowHorizontalOffset = (row % 2 != 0 ? HEX_HORIZONTAL_OFFSET: 0);
        point.setX(Math.round(column * HEX_WIDTH + currentRowHorizontalOffset));
        point.setY(Math.round(row * HEX_VERTICAL_DISTANCE));
        return point;
    }

    public Vector2D<Integer> getSizeInPixels() {
        Vector2D<Integer> mapSize = new Vector2D<>();
        float maxHorizontalOffset = (height > 1 ? HEX_HORIZONTAL_OFFSET : 0);
        mapSize.setX(Math.round(width * HEX_WIDTH + maxHorizontalOffset));
        mapSize.setY(Math.round(HEX_HEIGHT + (height - 1) * HEX_VERTICAL_DISTANCE));
        return mapSize;
    }

    public long getMapId() {
        return mapId;
    }

    public String getMapName() {
        return mapName;
    }

    public int getRevision() {
        return revision;
    }

    public String getCreator() {
        return creator;
    }

    public int getPlayers() {
        return players;
    }

    public int getStartCredits() {
        return startCredits;
    }

    public int getIncome() {
        return income;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
