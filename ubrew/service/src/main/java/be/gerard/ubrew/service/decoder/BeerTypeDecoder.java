package be.gerard.ubrew.service.decoder;

import be.gerard.common.decoder.Decoder;
import be.gerard.ubrew.interface_v1.model.product.BeerType;
import be.gerard.ubrew.service.dao.BeerTypeDao;
import be.gerard.ubrew.service.model.product.BeerTypeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BeerTypeDecoder
 *
 * @author Bart Gerard
 */
@Component
public class BeerTypeDecoder implements Decoder<BeerTypeRecord, BeerType> {

    @Autowired
    private BeerTypeDao beerTypeDao;

    @Override
    public BeerTypeRecord decode(BeerType beerType) {
        BeerTypeRecord beerTypeRecord = beerType.getId() != null ? beerTypeDao.findOne(beerType.getId()) : null;
        
        if (beerTypeRecord == null) {
            beerTypeRecord = new BeerTypeRecord();
        }

        beerTypeRecord.setLabelKey(beerType.getLabelKey());
        beerTypeRecord.setDescription(beerType.getDescription());
        beerTypeRecord.setAlcoholMin(beerType.getAlcoholMin());
        beerTypeRecord.setAlcoholMax(beerType.getAlcoholMax());
        beerTypeRecord.setColorMin(beerType.getColorMin());
        beerTypeRecord.setColorMax(beerType.getColorMax());
        beerTypeRecord.setBitterMin(beerType.getBitterMin());
        beerTypeRecord.setBitterMax(beerType.getBitterMax());

        return beerTypeRecord;
    }

    @Override
    public BeerType encode(BeerTypeRecord beerTypeRecord) {
        BeerType beerType = new BeerType();
        beerType.setId(beerTypeRecord.getId());
        beerType.setLabelKey(beerTypeRecord.getLabelKey());
        beerType.setDescription(beerTypeRecord.getDescription());
        beerType.setAlcoholMin(beerTypeRecord.getAlcoholMin());
        beerType.setAlcoholMax(beerTypeRecord.getAlcoholMax());
        beerType.setColorMin(beerTypeRecord.getColorMin());
        beerType.setColorMax(beerTypeRecord.getColorMax());
        beerType.setBitterMin(beerTypeRecord.getBitterMin());
        beerType.setBitterMax(beerTypeRecord.getBitterMax());
        return beerType;
    }

}
