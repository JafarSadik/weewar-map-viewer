package com.github.weewar.mapviewer.service.impl

import com.github.weewar.mapviewer.model.*
import com.github.weewar.mapviewer.model.enums.*
import com.github.weewar.mapviewer.service.ImageRepository
import com.github.weewar.mapviewer.service.impl.keys.TerrainImageKey
import com.github.weewar.mapviewer.service.impl.keys.UnitImageKey
import org.springframework.stereotype.Service
import org.yaml.snakeyaml.Yaml
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.ConcurrentHashMap
import javax.imageio.ImageIO

@Service
class ImageRepositoryImpl : ImageRepository {

    private val terrainImages = ConcurrentHashMap<TerrainImageKey, Image>(50, 0.75f, 1)
    private val unitImages = ConcurrentHashMap<UnitImageKey, Image>(50, 0.75f, 1)

    @Throws(ImageNotFoundException::class)
    override fun getTerrain(terrainType: TerrainType, owner: Owner?, direction: Direction?): Image =
            terrainImages[TerrainImageKey(terrainType, owner, direction)]
                    ?: throw ImageNotFoundException("Terrain image not found: type = $terrainType, owner = $owner, direction = $direction")

    @Throws(ImageNotFoundException::class)
    override fun getUnit(unitType: UnitType, owner: Owner): Image =
            unitImages[UnitImageKey(unitType, owner)]
                    ?: throw ImageNotFoundException("Unit image not found: type = $unitType, owner = $owner")

    @Throws(ImagePreloadException::class)
    override fun preloadImages() =
            try {
                preloadTerrainImages()
                preloadUnitImages()
            } catch (e: IOException) {
                throw ImagePreloadException(e)
            }

    @Throws(IOException::class)
    private fun preloadTerrainImages() = withConfigFile(terrainImagesConfig) { terrainImage, terrainInfo ->
        val terrainType = TerrainType.of(terrainInfo["type"]!!)
        val owner = Owner.of(terrainInfo["owner"])
        val direction = Direction.of(terrainInfo["direction"])
        terrainImages[TerrainImageKey(terrainType, owner, direction)] = terrainImage
    }

    @Throws(IOException::class)
    private fun preloadUnitImages() = withConfigFile(unitImagesConfig) { unitImage, unitInfo ->
        val unitType = UnitType.of(unitInfo["type"])
        val owner = Owner.of(unitInfo["owner"])
        unitImages[UnitImageKey(unitType!!, owner!!)] = unitImage
    }

    @Throws(IOException::class)
    private fun withConfigFile(configFile: String, configEntryConsumer: (image: BufferedImage, imageInfo: Map<String, String?>) -> Unit) {
        javaClass.getResourceAsStream(configFile).use { inputStream ->
            val config = loadConfig(inputStream)
            for (file in config.keys) {
                val imageInfo = config[file] as Map<String, String?>
                val image = ImageIO.read(javaClass.getResource(file))
                configEntryConsumer(image, imageInfo)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun loadConfig(inputStream: InputStream): Map<String, Map<String, String?>> =
            Yaml().loadAs(inputStream, Map::class.java) as Map<String, Map<String, String?>>
}
