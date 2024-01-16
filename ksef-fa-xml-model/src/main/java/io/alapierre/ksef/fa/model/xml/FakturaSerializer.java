package io.alapierre.ksef.fa.model.xml;

import lombok.val;
import pl.com.softproject.utils.xml.BaseXMLSerializer;
import pl.com.softproject.utils.xml.XMLValidator;
import pl.gov.crd.wzor._2023._06._29._12648.Faktura;
import pl.gov.crd.wzor._2023._06._29._12648.TKodFormularza;
import pl.gov.crd.wzor._2023._06._29._12648.TNaglowek;

import javax.xml.XMLConstants;
import java.util.Set;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
public class FakturaSerializer extends BaseXMLSerializer<Faktura> {

    public FakturaSerializer() {
        super(
                "pl.gov.crd.wzor._2023._06._29._12648",
                "Schemat_FA_VAT(2)_v1-0E.xsd",
                "http://crd.gov.pl/wzor/2023/06/29/12648/ http://crd.gov.pl/wzor/2023/06/29/12648/schemat.xsd",
                Set.of(new XMLValidator.SchemaFactoryFeature(XMLConstants.FEATURE_SECURE_PROCESSING,false)));
    }

    public Faktura create() {
        return create("FA (2)", "1-0E", (byte) 2);
    }

    public Faktura create(String kodSystemowy, String wersjaSchemy, byte wariant) {

        Faktura faktura = new Faktura();

        TNaglowek naglowek = new TNaglowek();
        TNaglowek.KodFormularza kodFormularza = new TNaglowek.KodFormularza();
        kodFormularza.setKodSystemowy(kodSystemowy);
        kodFormularza.setWersjaSchemy(wersjaSchemy);
        kodFormularza.setValue(TKodFormularza.FA);

        naglowek.setKodFormularza(kodFormularza);
        naglowek.setWariantFormularza(wariant);

        faktura.setNaglowek(naglowek);

        return faktura;
    }

}
