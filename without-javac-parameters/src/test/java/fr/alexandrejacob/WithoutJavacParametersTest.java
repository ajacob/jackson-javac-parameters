package fr.alexandrejacob;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WithoutJavacParametersTest {

    @Test
    void test_deserialization() throws JsonProcessingException {
        var jsonPayload = """
                {
                    "key1": "val1",
                    "key2": "val2"
                }
                """;

        Payload payload = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .findAndRegisterModules()
                .readValue(jsonPayload, Payload.class);

        Assertions.assertEquals(payload.getKey1(), "val1");
        Assertions.assertEquals(payload.getKey2(), "val2");
    }

    @Test
    void test_serialization() throws JsonProcessingException {
        Payload payload = new Payload("val1", "val2");

        String jsonPayload = new ObjectMapper()
                .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS) // deterministic output
                .enable(SerializationFeature.INDENT_OUTPUT)
                .findAndRegisterModules()
                .writeValueAsString(payload);

        Assertions.assertEquals("""
                {
                  "key2" : "val2",
                  "key1" : "val1"
                }""", jsonPayload);
    }
}
