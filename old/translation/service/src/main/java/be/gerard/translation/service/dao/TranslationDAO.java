package be.gerard.translation.service.dao;

import be.gerard.translation.service.model.TranslationRecord;

/**
 *
 * @author bartgerard
 */
public interface TranslationDAO extends DAO<TranslationRecord, Long> {
    
    TranslationRecord get(final String application, final String language, final String key);
    
}
