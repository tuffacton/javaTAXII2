package xor.bcmc.taxii2.resources;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.Constants;
import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.JsonHandler;
import xor.bcmc.taxii2.validation.Errors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <a href="https://www.oasis-open.org/committees/download.php/59353/TAXII2.0Specification-draft3.pdf">ApiRoot Resource</a>
 */
public class ApiRoot extends TaxiiResource implements Identifiable<String> {
    public static final String RESOURCE_TYPE = "ApiRoot";

    @Expose(serialize = false)
    private String id;

    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    private List<String> versions;

    @Expose
    private Integer maxContentLength;

    @Expose
    private Map<String, JsonElement> extraFields = new HashMap<>();

    // TODO TAXII 2.1
    // private List<Channel> channels;
    // private List<Collection> collection;

    /**
     * Construct an ApiRoot resource
     *
     * @param title A human readable text/plain name used to identify this API instance. This is not the name of
     *                    this API Root that is found in the URL.
     * @param versions Lists the versions of TAXII that this API Root is compatible with. taxii-2.0 MUST be included in
     *                 this list to indicate conformance with this specification.
     */
    public ApiRoot(String title, List<String> versions) {
        this.title = title;
        this.versions = versions;
    }

    public ApiRoot() {
    }

    public static ApiRoot fromJson(String json) {
        return JsonHandler.getInstance().fromJson(json, ApiRoot.class);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getVersions() {
        return versions;
    }

    public Integer getMaxContentLength() {
        return maxContentLength;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public void setMaxContentLength(Integer maxContentLength) {
        this.maxContentLength = maxContentLength;
    }

    public ApiRoot withTitle(String title) {
        this.title = title;
        return this;
    }

    public ApiRoot withDescription(String description) {
        this.description = description;
        return this;
    }

    public ApiRoot withVersions(List<String> versions) {
        this.versions = versions;
        return this;
    }

    public ApiRoot withMaxContentLength(Integer maxContentLength) {
        this.maxContentLength = maxContentLength;
        return this;
    }

    public ApiRoot withExtraField(String field, JsonElement value) {
        this.extraFields.put(field, value);
        return this;
    }

    public Map<String, JsonElement> getExtraFields() {
        return extraFields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiRoot apiRoot = (ApiRoot) o;
        return Objects.equals(title, apiRoot.title) &&
                Objects.equals(description, apiRoot.description) &&
                Objects.equals(versions, apiRoot.versions) &&
                Objects.equals(maxContentLength, apiRoot.maxContentLength);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, description, versions, maxContentLength);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiRoot withId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public Errors validate() {
        Errors errors = new Errors();
        errors.rejectIfNullOrEmpty("title", this.title);
        errors.rejectIfNullOrEmpty("versions", this.versions);
        /*
            taxii_spec_compliance(4.2.1)
                (66) A value of taxii-2.0 MUST be present in the api-root versions field to indicate conformance with this specification
         */
        errors.rejectIfNotContains("versions", this.versions, Constants.TAXII_20);
        return errors;
    }

    @Override
    public boolean isValid() {
        return validate().isEmpty();
    }
}
