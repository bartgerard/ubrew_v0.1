package be.gerard.core.interface_v1.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Entry
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class Entry {

    private Content content;

    private String customDescription;

    private final Set<Performer> performers = new HashSet<>();

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getCustomDescription() {
        return customDescription;
    }

    public void setCustomDescription(String customDescription) {
        this.customDescription = customDescription;
    }

    public Set<Performer> getPerformers() {
        return performers;
    }

}
