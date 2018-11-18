package com.github.weewar.mapviewer.controllers

import com.fasterxml.jackson.core.type.TypeReference
import com.github.weewar.mapviewer.dao.MapSearchCriteria
import com.github.weewar.mapviewer.dao.WeewarMapDAO
import com.github.weewar.mapviewer.model.MapHeader
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [SearchPageController::class])
class SearchPageControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var dao: WeewarMapDAO;

    val maps = listOf(
            MapHeader(id = 1, mapName = "Botanic Troubles", revision = 0, creator = "bert", players = 2, income = 100, startCredits = 250, width = 20, height = 19),
            MapHeader(id = 2, mapName = "Bad lands", revision = 1, creator = "adam", players = 3, income = 50, startCredits = 250, width = 60, height = 60),
            MapHeader(id = 3, mapName = "Midway", revision = 2, creator = "steven", players = 4, income = 150, startCredits = 250, width = 10, height = 10),
            MapHeader(id = 4, mapName = "Northern Greece", revision = 3, creator = "unknown", players = 3, income = 150, startCredits = 250, width = 10, height = 10),
            MapHeader(id = 5, mapName = "D-Day", revision = 5, creator = "jafar", players = 2, income = 150, startCredits = 250, width = 10, height = 10)
    )

    @Test
    fun `expect all requested maps`() {
        `when`(dao.findMapHeaders(MapSearchCriteria(0, 100))).thenReturn(maps)

        val response = mockMvc.perform(get("/api/maps")
                .param("first", "0")
                .param("count", "100"))
                .andExpect(status().is2xxSuccessful)
                .toObject(object : TypeReference<List<MapHeader>>() {})

        assertThat(response).containsExactlyElementsOf(maps)
    }

    @Test
    fun `expect empty array when no maps defined`() {
        mockMvc.perform(get("/api/maps")
                .param("first", "0")
                .param("count", "5"))
                .andExpect(content().string("[]"))
    }
}