package be.gerard.general.service.dao;

import be.gerard.common.db.dao.DAO;
import be.gerard.general.service.model.TranslationRecord;
import java.util.List;

/**
 *
 * @author bartgerard
 */
public interface TranslationDAO extends DAO<TranslationRecord, Long> {
    
    TranslationRecord find(final String application, final String language, final String key);

    List<TranslationRecord> findAll(final String application);
    
    List<String> findAllSupportedLanguages();
    
}
