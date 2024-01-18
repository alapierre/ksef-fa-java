package io.alapierre.gobl.core.signature;

import lombok.Data;
import lombok.Value;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.18
 */
class EcdsaSignerTest {

    @Test
    void testSign() {

        EcdsaSigner signer = new EcdsaSigner();
        KeySupport keySupport = new KeySupport();
        val keys = keySupport.generate();

        val signature = signer.sign(keys.privateKey(), new Model("Jan Kowalski", 11));
        signer.verify(keys.publicKey(), signature);

    }

    @Value
    private static class Model {
        String name;
        int age;
    }

}
