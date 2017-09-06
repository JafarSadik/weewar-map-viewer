package com.github.weewar.mapviewer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.github.weewar.mapviewer.model.Constants.HEX_HEIGHT;
import static com.github.weewar.mapviewer.model.Constants.HEX_WIDTH;

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
        Vector2D.Mutable<Integer> point = new Vector2D.Mutable<>();
        double horizontalOffset = (row % 2 != 0 ? 0.5 * HEX_WIDTH : 0);
        double verticalDistance = 0.75 * HEX_HEIGHT;
        point.setX((int) (column * HEX_WIDTH + horizontalOffset));
        point.setY((int) (row * verticalDistance));
        return point.immutable();
    }

    public Vector2D<Integer> getSizeInPixels() {
        Vector2D.Mutable<Integer> mapSize = new Vector2D.Mutable<>();
        double maxHorizontalOffset = (height > 1 ? 0.5 * HEX_WIDTH : 0);
        double verticalDistance = 0.75 * HEX_HEIGHT;
        mapSize.setX((int) (width * HEX_WIDTH + maxHorizontalOffset));
        mapSize.setY((int) (HEX_HEIGHT + (height - 1) * verticalDistance));
        return mapSize.immutable();
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
