package be.gerard.core.service.dao;

import be.gerard.core.service.model.TranslationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TranslationDao
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-02-03 20:13
 */
public interface TranslationDao extends JpaRepository<TranslationRecord, Long> {

    TranslationRecord findByApplicationAndPrefixAndLanguageAndKey(String application, String prefix, String language, String key);

    List<TranslationRecord> findByApplication(String application);

}
