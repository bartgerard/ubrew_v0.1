package be.gerard.ubrew.service.model.product;

import be.gerard.ubrew.service.model.ProductRecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Beer
 *
 * @author Bart Gerard
 */
@Entity
@Table(name = "ubrew_product_beer")
public abstract class BeerRecord extends ProductRecord {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_beer_type_id")
    private BeerTypeRecord beerType;
    
    @Column(name = "alcohol", nullable = false, length = 3)
    private Integer alcohol;

    @Column(name = "color", nullable = false, length = 3)
    private Integer color;

    @Column(name = "bitter", nullable = false, length = 3)
    private Integer bitter;
    
    

}
