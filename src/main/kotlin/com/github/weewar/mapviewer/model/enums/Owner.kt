package com.github.weewar.mapviewer.model.enums

enum class Owner {
    blue,
    red,
    purple,
    yellow,
    green,
    white;

    companion object {
        fun of(str: String?): Owner? {
            return if (str != null) valueOf(str) else null
        }
    }
}


