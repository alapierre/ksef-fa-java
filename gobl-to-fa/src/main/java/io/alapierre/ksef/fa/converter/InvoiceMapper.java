package io.alapierre.ksef.fa.converter;

import lombok.val;
import org.gobl.model.Address;
import org.gobl.model.Invoice;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.gov.crd.wzor._2023._06._29._12648.Faktura;
import pl.gov.crd.wzor._2023._06._29._12648.TAdres;
import pl.gov.crd.wzor._2023._06._29._12648.TKodFormularza;
import pl.gov.crd.wzor._2023._06._29._12648.TNaglowek;
import pl.gov.crd.xml.schematy.dziedzinowe.mf._2022._01._05.ed.definicjetypy.TKodKraju;

import java.util.List;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
@Mapper
public interface InvoiceMapper {

    @Mapping(target = "fa.kodWaluty", source = "currency")
    @Mapping(target = "fa.p1", source = "issueDate")
    @Mapping(target = "fa.p2", source = "code")
    @Mapping(target = "fa.p131", source = "totals.sum")
    @Mapping(target = "podmiot1.daneIdentyfikacyjne.nazwa", source = "supplier.name")
    @Mapping(target = "podmiot1.daneIdentyfikacyjne.NIP", source = "supplier.taxId.code")
    @Mapping(target = "podmiot1.adres", source = "supplier.addresses")
    @Mapping(target = "podmiot2.daneIdentyfikacyjne.nazwa", source = "customer.name")
    @Mapping(target = "podmiot2.daneIdentyfikacyjne.NIP", source = "customer.taxId.code")
    @Mapping(target = "podmiot2.adres", source = "customer.addresses")
    Faktura invoiceToFaktura(Invoice invoice);

    Invoice fakturaToInvoice(Faktura faktura);

    default TAdres map(List<Address> value) {

        if(value.isEmpty()) return null;

        Address a = value.get(0);

        TAdres adres = new TAdres();
        if(a.getCountry() != null) adres.setKodKraju(TKodKraju.fromValue(a.getCountry()));
        adres.setAdresL1(a.getStreet() + " " + a.getNum());
        adres.setAdresL2(a.getCode() + ", " + a.getLocality());
        return adres;

    }


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
