package com.github.weewar.mapviewer.dao

class MapSearchCriteria {
    private var firstElement: Int = 0
    private var pageSize: Int = 0

    fun setFirstElement(firstElement: Int): MapSearchCriteria {
        this.firstElement = firstElement
        return this
    }

    fun getFirstElement(): Int {
        return firstElement
    }

    fun setPageSize(pageSize: Int): MapSearchCriteria {
        this.pageSize = pageSize
        return this
    }

    fun getPageSize(): Int {
        return pageSize
    }
}
