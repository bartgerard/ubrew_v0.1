package be.gerard.ubrew.core.service.model;

import be.gerard.common.db.model.BaseRecord;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ProductRecord
 *
 * @author Bart Gerard
 */
@Entity
@Table(name = "ubrew_product")
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_product", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ProductRecord extends BaseRecord {

}
