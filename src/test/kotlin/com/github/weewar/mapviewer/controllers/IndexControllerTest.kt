package com.github.weewar.mapviewer.controllers

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [IndexController::class])
class IndexControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `redirect to the search page from main context`() {
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("/search"))
    }

    @Test
    fun `redirect to the search page on server error`() {
        mockMvc.perform(get("/error"))
                .andExpect(redirectedUrl("/search"))
    }
}