package com.github.weewar.mapviewer.service.impl

import com.github.weewar.mapviewer.model.*
import com.github.weewar.mapviewer.service.ImageRepository
import com.github.weewar.mapviewer.service.WeewarMapRenderer
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.resizers.configurations.*
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
        val graph = weewarMapImage.graphics

        // fill with a transparent background
        graph.color = Color(0, 0, 0, 0)
        graph.fillRect(0, 0, mapWidth, mapHeight)

        // render terrain, buildings and units
        for (tile in weewarMap.terrain) {
            val (x, y) = weewarMap.hexToPixel(tile.x, tile.y)

            // render terrain and buildings
            val terrain = images.getTerrain(tile.type, tile.startFaction, tile.direction)
            graph.drawImage(terrain, x, y, null)

            // render units
            if (tile.unit != null && tile.unitOwner != null) {
                val unit = images.getUnit(tile.unit, tile.unitOwner)
                graph.drawImage(unit, x, y, null)
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
