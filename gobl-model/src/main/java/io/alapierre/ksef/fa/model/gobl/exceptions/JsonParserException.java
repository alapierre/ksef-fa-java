package io.alapierre.ksef.fa.model.gobl.exceptions;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.16
 */
public class JsonParserException extends RuntimeException {

    public JsonParserException() {
        super();
    }

    public JsonParserException(String message) {
        super(message);
    }

    public JsonParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonParserException(Throwable cause) {
        super(cause);
    }
}
