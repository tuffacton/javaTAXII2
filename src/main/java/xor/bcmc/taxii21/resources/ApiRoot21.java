package xor.bcmc.taxii21.resources;

import com.google.gson.annotations.Expose;
import xor.bcmc.taxii2.Constants;
import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.JsonHandler;
import xor.bcmc.taxii2.resources.TaxiiResource;
import xor.bcmc.taxii2.validation.Errors;

import java.util.List;

/**
 * <a href="https://www.oasis-open.org/committees/download.php/59353/TAXII2.0Specification-draft3.pdf">ApiRoot Resource</a>
 */
public class ApiRoot21 extends TaxiiResource implements Identifiable<String> {
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
    public ApiRoot21(String title, List<String> versions) {
        this.title = title;
        this.versions = versions;
    }

    public ApiRoot21() {
    }

    public static ApiRoot21 fromJson(String json) {
        return JsonHandler.getInstance().fromJson(json, ApiRoot21.class);
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

    public ApiRoot21 withTitle(String title) {
        this.title = title;
        return this;
    }

    public ApiRoot21 withDescription(String description) {
        this.description = description;
        return this;
    }

    public ApiRoot21 withVersions(List<String> versions) {
        this.versions = versions;
        return this;
    }

    public ApiRoot21 withMaxContentLength(Integer maxContentLength) {
        this.maxContentLength = maxContentLength;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiRoot21 withId(String id) {
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
        errors.rejectIfNotContains("versions", this.versions, Constants.MediaTypes.TAXII_21);
        errors.rejectIfLessThanOrEqual("maxContentLength", this.maxContentLength, 0);
        return errors;
    }

    @Override
    public boolean isValid() {
        return validate().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiRoot21 apiRoot = (ApiRoot21) o;

        if (title != null ? !title.equals(apiRoot.title) : apiRoot.title != null) return false;
        if (description != null ? !description.equals(apiRoot.description) : apiRoot.description != null) return false;
        if (versions != null ? !versions.equals(apiRoot.versions) : apiRoot.versions != null) return false;
        return maxContentLength != null ? maxContentLength.equals(apiRoot.maxContentLength) : apiRoot.maxContentLength == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (versions != null ? versions.hashCode() : 0);
        result = 31 * result + (maxContentLength != null ? maxContentLength.hashCode() : 0);
        return result;
    }
}
