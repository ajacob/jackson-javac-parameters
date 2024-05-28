# jackson-javac-parameters

The purpose of this project is to demonstrate a strange behavior when deserializing json using an object having a
`@JsonCreator` annotated constructor with `@JsonProperty` annotated parameters.

You can run the following command:

```shell
mvn clean verify --fail-at-end
```

And notice that the test from `with-javac-parameters` fails while the one from `without-javac-parameters` pass.
The code from both projects is the same, the only change is in the `pom.xml`:
- For `with-javac-parameters` the `maven-compiler-plugin` is configured with `<parameters>true</parameters>`
- For `without-javac-parameters` the `maven-compiler-plugin` is configured with `<parameters>false</parameters>`

For both project, the `jackson-module-parameter-names` dependency is in the classpath.

## The JSON payload

... is the following:

```json
{
    "key1": "val1",
    "key2": "val2"
}
```

## The java object

... used for data-binding is the following:

```java
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
```

NOTE: `key1` property is annotated with `@JsonProperty("key")` which is wrong regarding the JSON payload
