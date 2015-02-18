package be.gerard.common.to;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;

/**
 * BaseTo
 *
 * @author bartgerard
 * @version 0.0.1
 */
public abstract class BaseTo implements Serializable {

    @XmlElement
    private Long id;

    public BaseTo() {
    }

    public BaseTo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseTo other = (BaseTo) obj;
        return Objects.equals(this.id, other.id);
    }

}
