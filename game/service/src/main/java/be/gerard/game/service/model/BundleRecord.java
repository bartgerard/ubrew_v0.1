package be.gerard.game.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Bundle
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_bundle", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "bgc_bundle")
public class BundleRecord extends BaseRecord {

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "price", column = @Column(name = "cost")),
//            @AttributeOverride(name = "currency", column = @Column(name = "cost_curr"))
//    })
//    private Price price;

    @OneToMany
    @JoinColumn(name = "bundle_id", foreignKey = @ForeignKey(name = "fk_prd2bundle"))
    private final Set<ProductRecord> products = new HashSet<>();

}
