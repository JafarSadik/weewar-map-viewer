package com.github.weewar.mapviewer.service.impl

import com.github.weewar.mapviewer.model.WeewarMap
import com.github.weewar.mapviewer.service.ImageNotFoundException
import com.github.weewar.mapviewer.service.ImageRepository
import com.github.weewar.mapviewer.service.ImageResizeException
import com.github.weewar.mapviewer.service.WeewarMapRenderer
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.resizers.configurations.AlphaInterpolation
import net.coobird.thumbnailator.resizers.configurations.Antialiasing
import net.coobird.thumbnailator.resizers.configurations.Dithering
import net.coobird.thumbnailator.resizers.configurations.Rendering
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.IOException

@Service
class WeewarMapRendererImpl @Autowired
constructor(private val images: ImageRepository) : WeewarMapRenderer {

    @Throws(ImageNotFoundException::class)
    override fun render(weewarMap: WeewarMap): BufferedImage {
        // create empty image of a proper size
        val (mapWidth, mapHeight) = weewarMap.sizeInPixels()
        val weewarMapImage = BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB)
        val g = weewarMapImage.graphics

        // fill with a transparent background
        g.color = Color(0, 0, 0, 0)
        g.fillRect(0, 0, mapWidth, mapHeight)

        // render terrain, buildings and units
        for (tile in weewarMap.terrain) {
            val (x, y) = weewarMap.hexToPixel(tile.x, tile.y)
            // render terrain and buildings
            val terrain = images.getTerrain(tile.type, tile.startFaction, tile.direction)
            g.drawImage(terrain, x, y, null)

            // render units
            val unitExists = tile.unit != null
            if (unitExists) {
                val unit = images.getUnit(tile.unit!!, tile.unitOwner!!)
                g.drawImage(unit, x, y, null)
            }
        }
        return weewarMapImage
    }

    @Throws(ImageNotFoundException::class, ImageResizeException::class)
    override fun renderThumbnail(weewarMap: WeewarMap, width: Int, height: Int): BufferedImage {
        val weewarMapImage = render(weewarMap)
        try {
            return Thumbnails.of(weewarMapImage)
                    .size(width, height)
                    .alphaInterpolation(AlphaInterpolation.QUALITY)
                    .antialiasing(Antialiasing.ON)
                    .dithering(Dithering.DEFAULT)
                    .rendering(Rendering.QUALITY)
                    .keepAspectRatio(true)
                    .outputQuality(1.0)
                    .asBufferedImage()
        } catch (e: IOException) {
            throw ImageResizeException(e)
        }

    }
}
