package com.github.weewar.mapviewer.dao.memory

import com.github.weewar.mapviewer.dao.MapSearchCriteria
import com.github.weewar.mapviewer.model.MapHeader
import com.github.weewar.mapviewer.model.mapsDir
import com.github.weewar.mapviewer.service.impl.WeewarMapLoaderImpl
import com.github.weewar.mapviewer.utils.ClassPath
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.net.URL

class InMemoryWeewarMapDAOTest {
    val classPath = mock(ClassPath::class.java)
    val weewarMapLoader = WeewarMapLoaderImpl(classPath)
    val weewarMapDao = InMemoryWeewarMapDAO(weewarMapLoader, classPath)

    @Before
    fun initClassPathAndPreloadMaps() {
        val maps = listOf(url("/public/api/maps/1"), url("/public/api/maps/2"))
        `when`(classPath.resources(mapsDir)).thenReturn(maps)
        `when`(classPath.resource("/public/api/maps/1")).thenReturn(maps[0])
        `when`(classPath.resource("/public/api/maps/2")).thenReturn(maps[1])
        weewarMapDao.populate()
    }

    @Test
    fun `find map by id`() {
        val weewarMap = weewarMapDao.findMapById(1)
        assertTrue(weewarMap.isPresent)

        with(weewarMap.get()) {
            assertThat(header).usingRecursiveComparison().isEqualTo(
                    MapHeader(id = 1, mapName = "Three ways", revision = 0, creator = "bert", players = 3,
                            income = 200, startCredits = 100, width = 17, height = 20)
            )

            assertThat(terrain).hasSize(190)

            assertThat(sizeInPixels()).isEqualTo(Pair(560, 528))
        }
    }

    @Test
    fun `find map header by id`() {
        val weewarMapHeader = weewarMapDao.findMapHeaderById(2)
        assertTrue(weewarMapHeader.isPresent)

        assertThat(weewarMapHeader.get()).usingRecursiveComparison().isEqualTo(
                MapHeader(id = 2, mapName = "Botanic Troubles", revision = 0, creator = "bert", players = 2,
                        income = 100, startCredits = 250, width = 20, height = 19)
        )
    }

    @Test
    fun `find all map headers that satisfy search criteria`() {
        assertThat(weewarMapDao.findMapHeaders(MapSearchCriteria(0, 1000))).hasSize(2)
    }

    private fun url(resource: String): URL = ClassPath().resource(resource)
}