package xor.bcmc.taxii2.resources;

import xor.bcmc.taxii2.Identifiable;
import xor.bcmc.taxii2.JsonHandler;

import java.util.List;

public class Collection extends TaxiiResource implements Identifiable<String> {

    private String id;
    private String title;
    private String description;
    private boolean canRead = false;
    private boolean canWrite = false;
    private List<String> mediaTypes;

    public Collection() {
    }

    public Collection(String id, String title, boolean canRead, boolean canWrite) {
        this.id = id;
        this.title = title;
        this.canRead = canRead;
        this.canWrite = canWrite;
    }

    public static Collection fromJson(String json) {
        return JsonHandler.getInstance().fromJson(json, Collection.class);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean canRead() {
        return canRead;
    }

    public boolean canWrite() {
        return canWrite;
    }

    public List<String> getMediaTypes() {
        return mediaTypes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

    public void setMediaTypes(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public Collection withId(String id) {
        this.id = id;
        return this;
    }

    public Collection withTitle(String title) {
        this.title = title;
        return this;
    }

    public Collection withDescription(String description) {
        this.description = description;
        return this;
    }

    public Collection withCanRead(boolean canRead) {
        this.canRead = canRead;
        return this;
    }

    public Collection withCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
        return this;
    }

    public Collection withMediaTypes(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collection that = (Collection) o;

        if (canRead != that.canRead) return false;
        if (canWrite != that.canWrite) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!title.equals(that.title)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return mediaTypes != null ? mediaTypes.equals(that.mediaTypes) : that.mediaTypes == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (canRead ? 1 : 0);
        result = 31 * result + (canWrite ? 1 : 0);
        result = 31 * result + (mediaTypes != null ? mediaTypes.hashCode() : 0);
        return result;
    }
}
