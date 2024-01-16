package io.alapierre.ksef.fa.model.gobl;

import org.gobl.model.Invoice;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
public class InvoiceSerializer extends GoblSerializer<Invoice> {
    public InvoiceSerializer() {
        super(Invoice.class);
    }
}
