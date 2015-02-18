package be.gerard.ubrew.core.service.dao;

import be.gerard.common.db.dao.DAOHib;
import be.gerard.ubrew.core.service.model.product.BeerTypeRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 * BeerTypeDAO
 * 
 * @author bartgerard
 */
@Profile("hib")
@Repository
public class BeerTypeDAOHib extends DAOHib<BeerTypeRecord, Long> implements BeerTypeDAO {

}
