package io.alapierre.ksef.fa.model.xml;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.gov.crd.wzor._2023._06._29._12648.Faktura;
import pl.gov.crd.wzor._2023._06._29._12648.TAdres;

import java.io.File;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
class FakturaSerializerTest {

    @Test
    void parse() {

        FakturaSerializer serializer = new FakturaSerializer();
        Faktura invoice = serializer.fromFile(new File("src/test/resources/fa.xml"), true);

        Assertions.assertNotNull(invoice);
        Assertions.assertNotNull(invoice.getNaglowek());
    }

    @Test
    void serialize() {

        FakturaSerializer fakturaSerializer = new FakturaSerializer();

        Faktura invoice = fakturaSerializer.create();

        Faktura.Podmiot1 podmiot1 = new Faktura.Podmiot1();
        TAdres address = new TAdres();
        address.setAdresL1("ul. Kolejowa 123");
        address.setAdresL2("05-092 Łomianki");

        podmiot1.setAdres(address);
        invoice.setPodmiot1(podmiot1);

        val str = fakturaSerializer.toString(invoice, false);

        System.out.println(str);

        Assertions.assertNotNull(str);

    }



    }
