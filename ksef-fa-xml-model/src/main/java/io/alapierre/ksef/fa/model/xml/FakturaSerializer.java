package io.alapierre.ksef.fa.model.xml;

import pl.com.softproject.utils.xml.BaseXMLSerializer;
import pl.com.softproject.utils.xml.XMLValidator;
import pl.gov.crd.wzor.fa3.Faktura;
import pl.gov.crd.wzor.fa3.TKodFormularza;
import pl.gov.crd.wzor.fa3.TNaglowek;

import javax.xml.XMLConstants;
import java.util.Set;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
public class FakturaSerializer extends BaseXMLSerializer<Faktura> {

    public FakturaSerializer() {
        super(
                "pl.gov.crd.wzor.fa3",
                "Schemat_FA_VAT(3)_v1-0E.xsd",
                "http://crd.gov.pl/wzor/2025/06/25/13775/ http://crd.gov.pl/wzor/2025/06/25/13775/schemat.xsd",
                Set.of(new XMLValidator.SchemaFactoryFeature(XMLConstants.FEATURE_SECURE_PROCESSING,false)));
    }

    public Faktura create() {
        return create("FA (3)", "1-0E", (byte) 2);
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
