package be.gerard.core.interface_v1.model;

/**
 * Content
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class Content {

    private String name;

    private ContentType contentType;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
