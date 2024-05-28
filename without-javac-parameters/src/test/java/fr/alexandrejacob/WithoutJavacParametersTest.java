package fr.alexandrejacob;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WithoutJavacParametersTest {

    @Test
    void test() throws JsonProcessingException {
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
}
