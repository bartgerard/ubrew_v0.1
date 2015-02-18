package be.gerard.ubrew.core.service.dao;

import be.gerard.common.db.dao.DAOJpa;
import be.gerard.ubrew.core.service.model.product.BeerTypeRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 * BeerTypeDAO
 * 
 * @author bartgerard
 */
@Profile("jpa")
@Repository
public class BeerTypeDAOJpa extends DAOJpa<BeerTypeRecord, Long> implements BeerTypeDAO {

}
