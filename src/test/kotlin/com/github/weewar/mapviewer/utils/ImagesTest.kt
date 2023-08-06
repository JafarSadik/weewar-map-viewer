package com.github.weewar.mapviewer.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.awt.image.BufferedImage

class ImagesTest {
    @Test
    fun `expect non empty byte buffer`() {
        val image = BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB)
        assertThat(Images.toPNG(image)).isNotEmpty()
    }
}