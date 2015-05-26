package be.gerard.general.interface_v1;

import be.gerard.core.interface_v1.annotation.validation.Validation;
import be.gerard.core.interface_v1.exception_v1.ServiceException;
import be.gerard.general.interface_v1.enums.Language;
import be.gerard.general.interface_v1.model.Translation;
import be.gerard.general.interface_v1.session.UserSession;
import java.util.List;

/**
 *
 * @author bartgerard
 */
public interface TranslationService extends BaseService {
    
    Translation find(Long id);
    
    Translation find(String application, Language language, String key);
    
    String get(String application, Language language, String key);
    
    Translation saveOrUpdate(@Validation(validators = "translation") Translation translation, UserSession session) throws ServiceException;
    
    List<Translation> saveOrUpdate(@Validation(validators = "translation") List<Translation> translations, UserSession session) throws ServiceException;
    
    void remove(Translation translation, UserSession session);
    
    List<Translation> findAll();
    
    List<Translation> findAll(String application);
    
    List<String> findAllSupportedLanguages();
    
}
