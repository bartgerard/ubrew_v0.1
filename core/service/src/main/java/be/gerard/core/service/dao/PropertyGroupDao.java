
package be.gerard.core.service.dao;

import be.gerard.core.service.model.PropertyGroupRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PropertyGroupDao
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface PropertyGroupDao extends JpaRepository<PropertyGroupRecord, Long> {

    PropertyGroupRecord findByKey(String key);

}
