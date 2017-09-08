package com.github.weewar.mapviewer.service.impl.key;

import com.github.weewar.mapviewer.service.impl.keys.TerrainImageKey;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class TerrainImageKeyTest {
    @Test
    public void verifyEqualsContract() {
        EqualsVerifier.forClass(TerrainImageKey.class).verify();
    }
}