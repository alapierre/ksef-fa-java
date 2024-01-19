package io.alapierre.gobl.core.signature;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.security.EcPrivateJwk;
import io.jsonwebtoken.security.EcPublicJwk;
import io.jsonwebtoken.security.Jwks;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.UUID;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.18
 */
@Slf4j
public class KeySupport {

    private final ObjectMapper objectMapper = new ObjectMapper();

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

    public void save(ECPrivateKey key, Path path) throws IOException {
        Files.write(path, toJsonString(key).getBytes());
    }

    public void save(ECPublicKey key, Path path) throws IOException {
        Files.write(path, toJsonString(key).getBytes());
    }

    public Key loadKey(Path path) throws Exception {

        String jwkJson = Files.readString(path);

        val jwk = Jwks.parser()
                .build()
                .parse(jwkJson);

        return jwk.toKey();
    }

    public String toJsonString(ECPrivateKey key) {

        EcPrivateJwk secretJwk = Jwks.builder()
                .key(key)
                .id(UUID.randomUUID().toString())
                .build();

        return Jwks.UNSAFE_JSON(secretJwk);
    }

    public String toJsonString(ECPublicKey key) {

        EcPublicJwk secretJwk = Jwks.builder()
                .key(key)
                .id(UUID.randomUUID().toString())
                .build();

        return Jwks.UNSAFE_JSON(secretJwk);
    }
}
