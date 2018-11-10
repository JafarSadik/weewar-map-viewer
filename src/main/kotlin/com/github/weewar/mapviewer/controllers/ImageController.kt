package com.github.weewar.mapviewer.controllers

import com.github.weewar.mapviewer.dao.WeewarMapDAO
import com.github.weewar.mapviewer.model.*
import com.github.weewar.mapviewer.service.WeewarMapRenderer
import com.github.weewar.mapviewer.utils.Images
import org.springframework.http.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.awt.image.BufferedImage
import java.util.concurrent.TimeUnit

@Controller
class ImageController(private val weewarMapRenderer: WeewarMapRenderer, private val weewarMapDAO: WeewarMapDAO) {

    @GetMapping("/images/maps/{map_id}", produces = [MediaType.IMAGE_PNG_VALUE])
    fun renderWeewarMap(@PathVariable("map_id") mapId: Int): ResponseEntity<ByteArray> {
        return weewarMapDAO.findMapById(mapId)
                .map { httpOk(image(it)) }
                .orElse(httpNotFoundError())
    }

    @GetMapping("/images/maps/thumbnails/{map_id}")
    fun renderMapThumbnail(@PathVariable("map_id") mapId: Int): ResponseEntity<ByteArray> {
        return weewarMapDAO.findMapById(mapId)
                .map { httpOk(thumbnail(it)) }
                .orElse(httpNotFoundError())
    }

    private fun image(map: WeewarMap) = weewarMapRenderer.render(map)

    private fun thumbnail(map: WeewarMap) = weewarMapRenderer.renderThumbnail(map, thumbnailWidth, thumbnailHeight)

    private fun httpNotFoundError(): ResponseEntity<ByteArray> = ResponseEntity.notFound().build()

    private fun httpOk(image: BufferedImage): ResponseEntity<ByteArray> {
        val pngData = Images.toPNG(image)
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(pngData.size.toLong())
                .cacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .body(pngData)
    }
}
