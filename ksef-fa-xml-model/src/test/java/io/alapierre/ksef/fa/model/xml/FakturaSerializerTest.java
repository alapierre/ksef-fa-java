package io.alapierre.ksef.fa.model.xml;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
class FakturaSerializerTest {

    @Test
    void testSerialize() {

        FakturaSerializer serializer = new FakturaSerializer();
        val invoice = serializer.fromFile(new File("src/test/resources/fa.xml"), true);

        Assertions.assertNotNull(invoice);
        Assertions.assertNotNull(invoice.getNaglowek());
    }


}
