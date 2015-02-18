
package be.gerard.core.application.service.dao;

import be.gerard.core.application.service.model.ApplicationRecord;
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
