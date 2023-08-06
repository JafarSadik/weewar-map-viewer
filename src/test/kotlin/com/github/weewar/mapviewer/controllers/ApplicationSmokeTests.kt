package com.github.weewar.mapviewer.controllers

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl


@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ApplicationSmokeTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `search page`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/search"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(content().string(Matchers.containsString("map-box")))
    }

    @Test
    fun `redirect to search page`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(redirectedUrl("/search"))
    }

    @Test
    fun `map page`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/map/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(content().string(Matchers.containsString("map-container")))
    }
}