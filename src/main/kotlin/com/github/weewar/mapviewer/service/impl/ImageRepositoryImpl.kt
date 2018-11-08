package com.github.weewar.mapviewer.service.impl

import com.github.weewar.mapviewer.exceptions.ImageNotFoundException
import com.github.weewar.mapviewer.exceptions.ImagePreloadException
import com.github.weewar.mapviewer.model.AppPaths
import com.github.weewar.mapviewer.model.enums.Direction
import com.github.weewar.mapviewer.model.enums.Owner
import com.github.weewar.mapviewer.model.enums.TerrainType
import com.github.weewar.mapviewer.model.enums.UnitType
import com.github.weewar.mapviewer.service.ImageRepository
import com.github.weewar.mapviewer.service.impl.keys.TerrainImageKey
import com.github.weewar.mapviewer.service.impl.keys.UnitImageKey
import org.springframework.stereotype.Service
import org.yaml.snakeyaml.Yaml
import java.awt.Image
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap
import javax.imageio.ImageIO

@Service
class ImageRepositoryImpl : ImageRepository {

    private val terrainImages = ConcurrentHashMap<TerrainImageKey, Image>(50 /*max terrain images*/,
            0.75f /*default load factor*/, 1 /*single shard*/)
    private val unitImages = ConcurrentHashMap<UnitImageKey, Image>(200 /*max unit images*/,
            0.75f /*default load factor*/, 1 /*single shard*/)

    @Throws(ImageNotFoundException::class)
    override fun getTerrain(terrainType: TerrainType, owner: Owner?, direction: Direction?): Image = terrainImages[TerrainImageKey(terrainType, owner, direction)]
            ?: throw ImageNotFoundException("Terrain image not found: type = $terrainType, owner = $owner, direction = $direction")

    @Throws(ImageNotFoundException::class)
    override fun getUnit(unitType: UnitType, owner: Owner): Image = unitImages[UnitImageKey(unitType, owner)]
            ?: throw ImageNotFoundException("Unit image not found: type = $unitType, owner = $owner")

    @Throws(ImagePreloadException::class)
    override fun preloadImages() =
            try {
                val yaml = Yaml()
                preloadTerrainImages(yaml)
                preloadUnitImages(yaml)
            } catch (e: IOException) {
                throw ImagePreloadException(e)
            }

    @Throws(IOException::class)
    private fun preloadTerrainImages(yaml: Yaml) {
        javaClass.getResourceAsStream(AppPaths.terrainImages).use { inputStream ->
            val terrainFiles = yaml.loadAs(inputStream, Map::class.java) as Map<String, Map<String, String?>>
            for (terrainFile in terrainFiles.keys) {
                val terrainDetails = terrainFiles[terrainFile] as Map<String, String?>

                val terrainType = TerrainType.of(terrainDetails["type"]!!)
                val owner = Owner.of(terrainDetails.get("owner"))
                val direction = Direction.of(terrainDetails.get("direction"))
                val url = javaClass.getResource(terrainFile)
                val terrainImage = ImageIO.read(url)
                terrainImages[TerrainImageKey(terrainType, owner, direction)] = terrainImage
            }
        }
    }

    @Throws(IOException::class)
    private fun preloadUnitImages(yaml: Yaml) {
        javaClass.getResourceAsStream(AppPaths.unitImages).use { inputStream ->
            val unitFiles = yaml.loadAs(inputStream, Map::class.java) as Map<String, Map<String, String?>>
            for (unitFile in unitFiles.keys) {
                val unitDetails = unitFiles[unitFile] as Map<String, String?>
                val unitType = UnitType.of(unitDetails["type"])
                val owner = Owner.of(unitDetails["owner"])
                val url = javaClass.getResource(unitFile)
                val unitImage = ImageIO.read(url)
                unitImages[UnitImageKey(unitType!!, owner!!)] = unitImage
            }
        }
    }
}
