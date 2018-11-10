package com.github.weewar.mapviewer.controllers

import com.github.weewar.mapviewer.dao.WeewarMapDAO
import com.github.weewar.mapviewer.model.*
import com.github.weewar.mapviewer.service.WeewarMapRenderer
import com.github.weewar.mapviewer.utils.Images
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ImageController(private val weewarMapRenderer: WeewarMapRenderer, private val weewarMapDAO: WeewarMapDAO) {

    @GetMapping("/images/maps/{map_id}", produces = [MediaType.IMAGE_PNG_VALUE])
    fun renderWeewarMap(@PathVariable("map_id") mapId: Int): ResponseEntity<ByteArray> {
        return weewarMapDAO.findMapById(mapId)
                .map { weewarMapPNGImage(it) }
                .orElse(notFoundError())
    }

    @GetMapping("/images/maps/thumbnails/{map_id}")
    fun renderMapThumbnail(@PathVariable("map_id") mapId: Int): ResponseEntity<ByteArray> {
        return weewarMapDAO.findMapById(mapId)
                .map { weewarMapPNGThumbnail(it) }
                .orElse(notFoundError())
    }

    private fun weewarMapPNGImage(weewarMap: WeewarMap): ResponseEntity<ByteArray> {
        val image = weewarMapRenderer.render(weewarMap)
        val pngData = Images.toPNG(image)
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(pngData.size.toLong())
                .body(pngData)
    }

    private fun weewarMapPNGThumbnail(weewarMap: WeewarMap): ResponseEntity<ByteArray> {
        val thumbnail = weewarMapRenderer.renderThumbnail(weewarMap, thumbnailWidth, thumbnailHeight)
        val pngData = Images.toPNG(thumbnail)
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(pngData.size.toLong())
                .body(pngData)
    }

    private fun notFoundError(): ResponseEntity<ByteArray> {
        return ResponseEntity.notFound().build()
    }
}
