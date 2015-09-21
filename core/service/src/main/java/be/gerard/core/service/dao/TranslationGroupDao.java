package be.gerard.core.service.dao;

import be.gerard.core.service.model.TranslationGroupRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TranslationGroupDao
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface TranslationGroupDao extends JpaRepository<TranslationGroupRecord, Long> {

    TranslationGroupRecord findByKey(final String key);

}
