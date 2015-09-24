package be.gerard.core.service.dao;

import java.util.Optional;

/**
 * CustomTranslationDao
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface CustomTranslationDao {

    Optional<String> findByAppAndPrefixAndKeyAndLanguage(String app, String prefix, String key, String language);

}
