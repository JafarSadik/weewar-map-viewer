package com.github.weewar.mapviewer.service.impl.keys

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.Test

class UnitImageKeyTest {
    @Test
    fun `verify equals contract`() {
        EqualsVerifier.forClass(UnitImageKey::class.java).verify()
    }
}