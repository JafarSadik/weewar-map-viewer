package com.github.weewar.mapviewer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.github.weewar.mapviewer.model.MapConstants.*;


public class MapHeader {
    private final int mapId;
    private final String mapName;
    private final int revision;
    private final String creator;
    private final int players;
    private final int startCredits;
    private final int income;
    private final int width;
    private final int height;

    @JsonCreator
    public MapHeader(@JsonProperty("id") int mapId, @JsonProperty("name") String mapName, @JsonProperty("revision") int revision,
                     @JsonProperty("creator") String creator, @JsonProperty("players") int players, @JsonProperty("width") int width,
                     @JsonProperty("perBaseCredits") int income, @JsonProperty("initialCredits") int startCredits,
                     @JsonProperty("height") int height) {
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

    @JsonIgnore
    public Vector2D<Integer> getSize() {
        return new Vector2D<>(width, height);
    }

    public Vector2D<Integer> hexToPixel(int column, int row) {
        Vector2D<Integer> point = new Vector2D<>();
        float rowHorizontalOffset = (row % 2 != 0 ? hexHorizontalOffset : 0);
        point.setX(Math.round(column * hexWidth + rowHorizontalOffset));
        point.setY(Math.round(row * hexVerticalDistance));
        return point;
    }

    @JsonIgnore
    public Vector2D<Integer> getSizeInPixels() {
        Vector2D<Integer> mapSize = new Vector2D<>();
        float maxHorizontalOffset = (height > 1 ? hexHorizontalOffset : 0);
        mapSize.setX(Math.round(width * hexWidth + maxHorizontalOffset));
        mapSize.setY(Math.round(hexHeight + (height - 1) * hexVerticalDistance));
        return mapSize;
    }

    public String getUrlEncodedMapName() {
        return "undefined";
    }

    public int getMapId() {
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

    @Override
    public String toString() {
        return "MapHeader{" +
                "mapId=" + mapId +
                ", mapName='" + mapName + '\'' +
                ", revision=" + revision +
                ", creator='" + creator + '\'' +
                ", players=" + players +
                ", startCredits=" + startCredits +
                ", income=" + income +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
