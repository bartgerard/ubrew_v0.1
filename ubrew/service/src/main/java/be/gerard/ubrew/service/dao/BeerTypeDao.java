package be.gerard.ubrew.service.dao;

import be.gerard.ubrew.service.model.product.BeerTypeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BeerType
 *
 * @author bartgerard
 */
public interface BeerTypeDao extends JpaRepository<BeerTypeRecord, Long> {

}
