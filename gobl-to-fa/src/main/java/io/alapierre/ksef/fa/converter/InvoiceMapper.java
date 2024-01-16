package io.alapierre.ksef.fa.converter;

import org.gobl.model.Invoice;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.gov.crd.wzor._2023._06._29._12648.Faktura;
import pl.gov.crd.wzor._2023._06._29._12648.TKodFormularza;
import pl.gov.crd.wzor._2023._06._29._12648.TNaglowek;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
@Mapper
public interface InvoiceMapper {

    @Mapping(target = "fa.kodWaluty", source = "currency")
    @Mapping(target = "fa.p1", source = "issueDate")
    @Mapping(target = "fa.p2", source = "code")
    Faktura invoiceToFaktura(Invoice invoice);

    Invoice fakturaToInvoice(Faktura faktura);

    @AfterMapping
    default void fillHeader(@MappingTarget Faktura faktura) {

        TNaglowek naglowek = new TNaglowek();
        TNaglowek.KodFormularza kodFormularza = new TNaglowek.KodFormularza();
        kodFormularza.setKodSystemowy("FA (2)");
        kodFormularza.setWersjaSchemy("1-0E");
        kodFormularza.setValue(TKodFormularza.FA);

        naglowek.setKodFormularza(kodFormularza);
        naglowek.setWariantFormularza((byte) 2);

        faktura.setNaglowek(naglowek);

    }

}
