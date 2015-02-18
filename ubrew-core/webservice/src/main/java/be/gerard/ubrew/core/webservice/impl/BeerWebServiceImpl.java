package be.gerard.ubrew.core.webservice.impl;

import be.gerard.ubrew.core.interface_v1.BeerService;
import be.gerard.ubrew.core.interface_v1.model.product.BeerType;
import be.gerard.ubrew.core.webservice.BeerWebService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BeerWebServiceImpl
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Service
public class BeerWebServiceImpl implements BeerWebService {
    
    @Autowired
    BeerService beerService;
    
    @Override
    public String getVersion() {
        return "v1.0.0";
    }

    @Override
    public BeerType saveOrUpdateBeerType(BeerType beerType) {
        return beerService.saveOrUpdateBeerType(beerType);
    }

    @Override
    public List<BeerType> findAllBeerTypes() {
        return beerService.findAllBeerTypes();
    }

}
