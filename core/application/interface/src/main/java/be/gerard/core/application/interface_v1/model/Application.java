package be.gerard.core.application.interface_v1.model;

import be.gerard.common.to.BaseTo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Application
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-10 16:58
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "application")
public class Application extends BaseTo {

    @XmlElement(required = true)
    private String key;

    @XmlElement(required = true)
    private List<Property> properties = new ArrayList<>();

    public Application() {
    }

    public Application(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}
