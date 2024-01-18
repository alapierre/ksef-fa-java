package io.alapierre.gobl.core.signature;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.TreeMap;

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

}
