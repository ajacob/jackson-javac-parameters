package fr.alexandrejacob;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {

    private final String key1;
    private final String key2;

    @JsonCreator
    public Payload(
            @JsonProperty("key") String key1,
            @JsonProperty("key2") String key2
    ) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
}
