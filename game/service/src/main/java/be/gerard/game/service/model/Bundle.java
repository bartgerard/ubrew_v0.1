package be.gerard.game.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bundle
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_bundle", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "bgc_bundle")
public class Bundle extends BaseRecord {

    @Embedded
    private Price price;

    //private final Set<Product> products = new HashSet<>();

}
