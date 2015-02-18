package be.gerard.core.application.interface_v1.model;

import be.gerard.common.to.BaseTo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Property
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-10 22:32
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "property")
public class Property extends BaseTo {

    @XmlElement(required = true)
    private String key;

    @XmlElement(required = true)
    private String group;

    @XmlElement(required = true)
    private String value;

    public Property() {
    }

    public Property(String key, String group, String value) {
        this.key = key;
        this.group = group;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
