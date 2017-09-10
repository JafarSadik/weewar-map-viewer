package com.github.weewar.mapviewer.dao;

public class MapSearchCriteria {
    private int firstElement;
    private int pageSize;

    public MapSearchCriteria setFirstElement(int firstElement) {
        this.firstElement = firstElement;
        return this;
    }

    public int getFirstElement() {
        return firstElement;
    }

    public MapSearchCriteria setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }
}
