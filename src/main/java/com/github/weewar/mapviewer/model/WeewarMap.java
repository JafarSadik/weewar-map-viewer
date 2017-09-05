package com.github.weewar.mapviewer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
