package be.gerard.core.service.dao;

import be.gerard.core.service.model.ApplicationInstanceRecord;
import be.gerard.core.service.model.ApplicationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * ApplicationInstanceDao
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-10 19:11
 */
public interface ApplicationInstanceDao extends JpaRepository<ApplicationInstanceRecord, Long> {

    @Query(value = "select a from ApplicationInstanceRecord a where a.application.key like ?1 and a.reference like ?2")
    ApplicationInstanceRecord findByApplicationAndReference(String key, String reference);

    ApplicationInstanceRecord findByApplicationAndReference(ApplicationRecord applicationRecord, String reference);

}
