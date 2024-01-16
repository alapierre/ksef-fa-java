package io.alapierre.ksef.fa.model.gobl;

import lombok.val;
import org.gobl.model.Invoice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
class GoblSerializerTest {

    @Test
    void testParseFromFile() {

        val serializer = new InvoiceSerializer();

        val invoice = serializer.fromFile(new File("src/test/resources/invoice.json"));

        System.out.println(invoice);

        Assertions.assertNotNull(invoice);

    }

}
