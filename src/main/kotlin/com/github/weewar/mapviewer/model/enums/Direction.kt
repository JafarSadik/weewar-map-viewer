package com.github.weewar.mapviewer.model.enums

enum class Direction {
    ew,
    nesw,
    nwse;

    companion object {
        fun of(str: String?): Direction? {
            return if (str != null) valueOf(str) else null
        }
    }
}

