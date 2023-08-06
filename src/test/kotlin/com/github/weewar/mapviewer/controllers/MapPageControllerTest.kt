package com.github.weewar.mapviewer.controllers

import com.github.weewar.mapviewer.dao.WeewarMapDAO
import com.github.weewar.mapviewer.model.MapHeader
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@WebMvcTest(controllers = [MapPageController::class])
class MapPageControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var weewarMapDAO: WeewarMapDAO

    @Test
    fun `display map page by map identifier`() {
        val map = MapHeader(id = 1, mapName = "Botanic Troubles", revision = 0, creator = "bert", players = 2,
                income = 100, startCredits = 250, width = 20, height = 19)

        `when`(weewarMapDAO.findMapHeaderById(1)).thenReturn(Optional.of(map))

        mockMvc.perform(get("/map/1"))
                .andExpect(status().isOk)
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(content().string(containsString("Botanic Troubles")))
    }

    @Test
    fun `redirect to search page when map doesn't exist`() {
        `when`(weewarMapDAO.findMapHeaderById(12345)).thenReturn(Optional.empty())

        mockMvc.perform(get("/map/12345"))
                .andExpect(redirectedUrl("/search"))
    }
}