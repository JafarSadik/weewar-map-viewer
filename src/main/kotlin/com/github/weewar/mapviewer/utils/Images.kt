package com.github.weewar.mapviewer.utils

import com.github.weewar.mapviewer.service.ImageIOException
import java.awt.image.RenderedImage
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.imageio.ImageIO

object Images {
    @Throws(ImageIOException::class)
    fun toPNG(image: RenderedImage): ByteArray {
        try {
            val imageData = ByteArrayOutputStream()
            ImageIO.write(image, "png", imageData)
            return imageData.toByteArray()
        } catch (e: IOException) {
            throw ImageIOException(e)
        }

    }
}
