package be.gerard.ubrew.core.service.dao;

import be.gerard.ubrew.core.service.model.product.BeerTypeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BeerType
 *
 * @author bartgerard
 */
public interface BeerTypeDao extends JpaRepository<BeerTypeRecord, Long> {

}
