package io.alapierre.gobl.core.signature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.impl.lang.RedactedSupplier;
import io.jsonwebtoken.security.EcPrivateJwk;
import io.jsonwebtoken.security.Jwks;
import lombok.val;
import org.gobl.model.Envelope;
import org.gobl.model.Header;
import org.gobl.model.Invoice;
import org.gobl.model.Object;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.MessageDigest;
import java.util.*;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2024.01.18
 */
public class SampleTest {

    @Test
    void hash() throws Exception {

        val content = Files.readAllBytes(Path.of("src/test/resources/invoice.json"));
        val digest = MessageDigest.getInstance("SHA-256");

        val sha = digest.digest(content);
        val str = HexFormat.of().formatHex(sha);

        System.out.println(str);

    }

    @Test
    void canonical() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        JsonNode jsonNode = mapper.readTree(new File("src/test/resources/invoice.json"));

        TreeMap<String, Object> map = mapper.convertValue(jsonNode, TreeMap.class);
        String canonicalJson = mapper.writeValueAsString(map);

        System.out.println(canonicalJson);

        val digest = MessageDigest.getInstance("SHA-256");

        val sha = digest.digest(canonicalJson.getBytes());
        val str = HexFormat.of().formatHex(sha);

        System.out.println(str);

    }

    @Test
    void envelop() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        var envelope = new Envelope();

        Header header = new Header();
        val object = new org.gobl.model.Object();
        List<String> sigs = new ArrayList<>();
        sigs.add("signature1");
        sigs.add("signature2");

        envelope.set$schema("http://json-schema.org/draft/2020-12/schema");
        envelope.setHead(header);
        envelope.setDoc(object);
        envelope.setSigs(sigs);

        ObjectNode envelopNode = mapper.valueToTree(envelope);

        val invoice = new Invoice();
        invoice.setCode("standard");
        invoice.setIssueDate("2024-01-01");

        ObjectNode invoiceNode = mapper.valueToTree(invoice);
        invoiceNode.put("$schema", "https://gobl.org/draft-0/bill/invoice");

        envelopNode.set("doc", invoiceNode);

        String envelopJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(envelopNode);
        System.out.println(envelopJson);

    }

    @Test
    void loadKey() throws Exception {

        String jwkJson = Files.readString(Paths.get("src/test/resources/id_es256.pub.jwk"));

        val jwk = Jwks.parser()
                .build()
                .parse(jwkJson);

        Key key = jwk.toKey();

        Assertions.assertNotNull(key);

        EcPrivateJwk kk = (EcPrivateJwk) key;

    }

    @Test
    void save() throws JsonProcessingException {

        KeySupport keySupport = new KeySupport();
        val keys = keySupport.generate();

        val json = keySupport.toJsonString(keys.privateKey());
        Assertions.assertNotNull(json);
        System.out.println(json);
    }

    @Test
    void loadPrivateKey() throws Exception {
        KeySupport keySupport = new KeySupport();
        val res = keySupport.loadKey(Paths.get("src/test/resources/id_es256.jwk"));

        Assertions.assertNotNull(res);
        System.out.println(res.getAlgorithm());
        System.out.println(res.getFormat());
        System.out.println(res.getClass());
    }

    @Test
    void loadPublicKey() throws Exception {
        KeySupport keySupport = new KeySupport();
        val res = keySupport.loadKey(Paths.get("src/test/resources/id_es256.pub.jwk"));

        Assertions.assertNotNull(res);
        System.out.println(res.getAlgorithm());
        System.out.println(res.getFormat());
        System.out.println(res.getClass());
    }

    @Test
    void tt() {

        KeySupport keySupport = new KeySupport();
        val keys = keySupport.generate();

        EcPrivateJwk secretJwk = Jwks.builder()
                .key(keys.privateKey())
                .id(UUID.randomUUID().toString())
                .build();

        System.out.println(Jwks.UNSAFE_JSON(secretJwk));
    }

    private String extractValue(java.lang.Object object) {
        if (object instanceof String) return (String) object;
        else if (object instanceof RedactedSupplier<?> s) return s.get().toString();
        else return object.toString();
    }

}
