package be.gerard.core.service.dao;

import be.gerard.core.service.model.TranslationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TranslationDao
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-02-03 20:13
 */
public interface TranslationDao extends JpaRepository<TranslationRecord, Long> {

}
