package be.gerard.ubrew.service.validation;

import be.gerard.common.exception_v1.BusinessException;
import be.gerard.common.validation.Validation;
import be.gerard.ubrew.interface_v1.exception.error.BeerValidationError;
import be.gerard.ubrew.interface_v1.model.product.Beer;
import be.gerard.ubrew.service.dao.BeerTypeDao;
import be.gerard.ubrew.service.model.product.BeerTypeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * BeerValidation
 *
 * @author bartgerard
 */
@Component
public class BeerValidation implements Validation<Beer> {

//    @Autowired
//    private Validations validations;

    @Autowired
    private BeerTypeDao beerTypeDao;

    @Override
    public boolean isApplicable(Object object) {
        return object instanceof Beer;
    }

    @Override
    public void validate(final Beer beer, final Map<BusinessException.Error, String[]> errors) {
        if (beer == null) {
            errors.put(BeerValidationError.NULL, new String[]{});
        } else {
            BeerTypeRecord beerTypeRecord = beerTypeDao.findOne(beer.getBeerType().getId());

            if (beer.getBeerType() == null) {
                errors.put(BeerValidationError.TYPE_NULL, new String[]{});
            } else if (beerTypeRecord == null) {
                errors.put(BeerValidationError.TYPE_INVALID, new String[]{String.format("beerType#%d", beer.getBeerType().getId())});
            } else {

            }
        }
    }

}
