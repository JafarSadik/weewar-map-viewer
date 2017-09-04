package com.github.weewar.mapviewer.model;

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

    public WeewarMap(long mapId, String mapName, int revision, String creator, int players,
                     int startCredits, int income, int width, int height) {
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
