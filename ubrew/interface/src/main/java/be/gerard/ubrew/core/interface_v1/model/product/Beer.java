package be.gerard.ubrew.core.interface_v1.model.product;

import be.gerard.ubrew.core.interface_v1.model.Product;
import java.io.Serializable;

/**
 * Beer
 *
 * @author Bart Gerard
 */
public class Beer extends Product implements Serializable {

    private BeerType beerType;

    public BeerType getBeerType() {
        return beerType;
    }

    public void setBeerType(BeerType beerType) {
        this.beerType = beerType;
    }

}
