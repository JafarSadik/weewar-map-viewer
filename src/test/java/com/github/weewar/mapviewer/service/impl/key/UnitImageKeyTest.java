package com.github.weewar.mapviewer.service.impl.key;

import com.github.weewar.mapviewer.service.impl.keys.UnitImageKey;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class UnitImageKeyTest {
    @Test
    public void verifyEqualsContract() {
        EqualsVerifier.forClass(UnitImageKey.class).verify();
    }
}