
package be.gerard.core.service.dao;

import be.gerard.core.service.model.ApplicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ApplicationDao
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface ApplicationDao extends JpaRepository<ApplicationRecord, Long> {

    ApplicationRecord findByKey(String key);

}
