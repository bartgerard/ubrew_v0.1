package be.gerard.ubrew.interface_v1.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Unit
 *
 * @author Bart Gerard
 */
public class Unit implements Serializable {

    private Long id;

    private String code;

    public Unit() {
    }

    public Unit(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.code);
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
        final Unit other = (Unit) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "Unit{" + "id=" + id + ", code=" + code + '}';
    }

}
