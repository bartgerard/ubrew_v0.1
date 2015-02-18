package be.gerard.common.db.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * BaseRecord
 *
 * @author bartgerard
 * @version 0.0.1
 */
@MappedSuperclass
public abstract class SimpleBaseRecord implements Serializable {

    public static final String SEQUENCE_GENERATOR = "sequence_generator";

    public static final int SEQUENCE_ALLOCATION_SIZE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQUENCE_GENERATOR)
    private Long id;

    public SimpleBaseRecord() {
    }

    public SimpleBaseRecord(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleBaseRecord other = (SimpleBaseRecord) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("%s {id=%d}", getClass().getSimpleName(), id);
    }

}
