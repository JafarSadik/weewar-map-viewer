package com.github.weewar.mapviewer.service.impl.keys

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.Test

class TerrainImageKeyTest {
    @Test
    fun `verify equals contract`() {
        EqualsVerifier.forClass(TerrainImageKey::class.java).verify()
    }
}