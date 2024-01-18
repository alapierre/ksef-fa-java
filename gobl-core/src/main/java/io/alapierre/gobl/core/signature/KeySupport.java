package io.alapierre.gobl.core.signature;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.18
 */
@Slf4j
public class KeySupport {

    public KeyPairHolder<ECPrivateKey, ECPublicKey> generate() {

        try {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256);

            KeyPair keyPair = keyPairGenerator.genKeyPair();
            ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
            ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();

            return new KeyPairHolder<>(privateKey, publicKey);

        } catch (NoSuchAlgorithmException ex) {
            log.error("No Such Algorithm Exception", ex);
            throw new RuntimeException(ex);
        }

    }

}
