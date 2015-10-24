package be.gerard.game.service.model;

import be.gerard.common.db.model.BaseRecord;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany
    @JoinColumn(name = "bundle_id", foreignKey = @ForeignKey(name = "fk_prd2bundle"))
    private final List<ProductRecord> products = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinTable(
            name = "rel_bundle2price",
            joinColumns = @JoinColumn(name = "bundle_id"),
            inverseJoinColumns = @JoinColumn(name = "price_id"),
            foreignKey = @ForeignKey(name = "fk_bundle2price"),
            inverseForeignKey = @ForeignKey(name = "fk_price2bundle")
    )
    private final Set<PriceRecord> prices = new HashSet<>();

    public BundleRecord() {
    }

    public List<ProductRecord> getProducts() {
        return products;
    }

    public Set<PriceRecord> getPrices() {
        return prices;
    }

}
