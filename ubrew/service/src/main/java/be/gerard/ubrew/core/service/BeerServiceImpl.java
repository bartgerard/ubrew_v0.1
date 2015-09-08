package be.gerard.ubrew.core.service;

import be.gerard.ubrew.core.interface_v1.BeerService;
import be.gerard.ubrew.core.interface_v1.model.product.Beer;
import be.gerard.ubrew.core.interface_v1.model.product.BeerType;
import be.gerard.ubrew.core.service.dao.BeerTypeDao;
import be.gerard.ubrew.core.service.decoder.BeerTypeDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BeerServiceImpl
 * 
 * @author bartgerard
 */
@Service
public class BeerServiceImpl implements BeerService {
    
    @Autowired
    private BeerTypeDao beerTypeDao;
    
    @Autowired
    private BeerTypeDecoder beerTypeDecoder;

    @Override
    public Beer saveOrUpdate(final Beer beer) {
        return null;
    }

    @Override
    public BeerType saveOrUpdateBeerType(final BeerType beerType) {
        return beerTypeDecoder.encode(beerTypeDao.save(beerTypeDecoder.decode(beerType)));
    }
    
    @Override
    public List<BeerType> findAllBeerTypes() {
        return beerTypeDecoder.encode(beerTypeDao.findAll());
    }
    
}
