package xor.bcmc.taxii2.resources;

import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ApiRootTest {

    private static ApiRoot apiRoot;

    static {
        apiRoot = new ApiRoot();
        apiRoot.withTitle("Api Root 1");
        apiRoot.withDescription("Api Description");
        apiRoot.withMaxContentLength(10);
        apiRoot.withVersions(Arrays.asList("Version 1", "Version 2"));
    }

    @Test
    public void serializeAndDeserialize() {
        String json = apiRoot.toJson();
        System.out.println(json);
        assertEquals(ApiRoot.fromJson(json), apiRoot);

        String toString = apiRoot.toString();
        System.out.println(toString);
        assertEquals(ApiRoot.fromJson(toString), apiRoot);
    }

    @Test
    public void equivalience() {
        String json = "{\"title\":\"Api root 1 title\",\"description\":\"Api root 1 description\",\"versions\":[\"taxii-2.0\",\"taxii-2.1\"],\"max_content_length\":50}";
        ApiRoot apiRoot1 = ApiRoot.fromJson(json);
        ApiRoot apiRoot2 = ApiRoot.fromJson(json);
        assertEquals(apiRoot1, apiRoot2);
    }

    /*
    {
        "title": "Api Root 1",
        "description": "API Root Description",
        "versions": [
            "taxii-2.0"
        ],
        "max_content_length": 0,
        "x_flarecloud_field1":"value1",
        "x_flarecloud_field2":{"key2":"value2"},
        "x_flarecloud_field3":["value3a","value3b"]
    }
     */
    @Test
    public void deserializeCustomProperties() {
        String json = "    {\n" +
                "        \"title\": \"Api Root 1\",\n" +
                "        \"description\": \"API Root Description\",\n" +
                "        \"versions\": [\n" +
                "            \"taxii-2.0\"\n" +
                "        ],\n" +
                "        \"max_content_length\": 0,\n" +
                "        \"x_flarecloud_field1\":\"value1\",\n" +
                "        \"x_flarecloud_field2\":{\"key2\":\"value2\"},\n" +
                "        \"x_flarecloud_field3\":[\"value3a\",\"value3b\"]\n" +
                "    }";
        ApiRoot apiRoot = ApiRoot.fromJson(json);
        assertThat(apiRoot.getCustomProperties().get("x_flarecloud_field1").getAsString(), equalTo("value1"));
        assertThat(apiRoot.getCustomProperties().get("x_flarecloud_field2").getAsJsonObject().get("key2").getAsString(), equalTo("value2"));
        assertThat(apiRoot.getCustomProperties().get("x_flarecloud_field3").getAsJsonArray(), equalTo(new JsonParser().parse("[\"value3a\",\"value3b\"]").getAsJsonArray()));
    }

    @Test
    public void idNotSerialized() {
        ApiRoot apiRoot = new ApiRoot();
        apiRoot.withId("ShouldBeHidden");
        System.out.println(apiRoot);
        assertFalse(apiRoot.toJson().contains("ShouldBeHidden"));
    }
}