package be.gerard.common.db.model;

import javax.persistence.MappedSuperclass;

/**
 * AbstractDefinition
 *
 * @author bartgerard
 * @version v0.0.1
 */
@MappedSuperclass
public abstract class DefinitionRecord extends BaseRecord {

    private String key;

    private String labelKey;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

}
