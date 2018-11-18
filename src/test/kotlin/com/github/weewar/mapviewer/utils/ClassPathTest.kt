package com.github.weewar.mapviewer.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ClassPathTest {
    val classPath = ClassPath()

    @Test
    fun `should list all resources by location`() {
        assertThat(classPath.resources("/public/api/maps/")).hasSize(12246)
    }

    @Test
    fun `should return a single class-path resource`() {
        assertThat(classPath.resource("/public/api/maps/1")).isNotNull()

        assertThat(classPath.resource("/public/api/maps/1").toString())
                .matches("file:/.*/public/api/maps/1")
    }
}