package be.gerard.ubrew.core.interface_v1;

import be.gerard.common.annotation.validation.Validation;
import be.gerard.ubrew.core.interface_v1.model.product.Beer;
import be.gerard.ubrew.core.interface_v1.model.product.BeerType;
import java.util.List;

/**
 *
 * @author bartgerard
 */
public interface BeerService {

    Beer saveOrUpdate(@Validation(validators = "beer") Beer beer);

    BeerType saveOrUpdateBeerType(BeerType beerType);
    
    List<BeerType> findAllBeerTypes();

}
