package xor.bcmc.taxii2.resources;

import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.junit.Test;
import xor.bcmc.taxii2.JsonHandler;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ManifestResourceTest {
    @Test
    public void serializeAndDeserialize() {
        ManifestEntry entry = new ManifestEntry();
        entry.withId("id1");

        ManifestResource manifestResource = new ManifestResource();
        manifestResource.setObjects(
                Arrays.asList(entry));

        String json = manifestResource.toJson();
        ManifestResource manifestResource_ = JsonHandler.getInstance().getGson().fromJson(json, ManifestResource.class);
        assertTrue(manifestResource.equals(manifestResource_));
    }

    @Test
    public void serializeAndDeserializeEmptyList() {
        ManifestResource manifestResource = new ManifestResource();

        String json = manifestResource.toJson();
        ManifestResource manifestResource_ = JsonHandler.getInstance().getGson().fromJson(json, ManifestResource.class);
        assertTrue(manifestResource.equals(manifestResource_));
    }

    @Test
    public void withoutOptionalFieldsEquals() {
        ManifestResource manifestResource1 = new ManifestResource();
        ManifestResource manifestResource2 = new ManifestResource();

        assertTrue(manifestResource1.equals(manifestResource2));
    }

    @Test
    public void withoutOptionalFieldsValidate() {
        ManifestResource manifestResource = new ManifestResource();

        assertTrue(manifestResource.validate().keySet().size() == 0);
    }

    @Test
    public void customPropertiesNotSerialized() {
        ManifestResource manifestResource = new ManifestResource();
        manifestResource.withCustomProperty("key", new JsonPrimitive("value"));
        System.out.println(manifestResource);
        assertFalse(manifestResource.toJson().contains("custom_properties"));
    }
}