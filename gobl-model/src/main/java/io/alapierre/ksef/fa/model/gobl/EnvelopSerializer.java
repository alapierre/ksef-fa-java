package io.alapierre.ksef.fa.model.gobl;

import lombok.NonNull;
import org.gobl.model.Envelope;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.19
 */
public class EnvelopSerializer extends GoblSerializer<Envelope> {
    public EnvelopSerializer() {
        super(Envelope.class);
    }
}
