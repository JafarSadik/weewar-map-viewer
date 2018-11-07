package com.github.weewar.mapviewer.service

import com.github.weewar.mapviewer.exceptions.ImageNotFoundException
import com.github.weewar.mapviewer.exceptions.ImageResizeException
import com.github.weewar.mapviewer.model.WeewarMap
import java.awt.image.BufferedImage

interface WeewarMapRenderer {
    @Throws(ImageNotFoundException::class)
    fun render(weewarMap: WeewarMap): BufferedImage

    @Throws(ImageNotFoundException::class, ImageResizeException::class)
    fun renderThumbnail(weewarMap: WeewarMap, width: Int, height: Int): BufferedImage
}
