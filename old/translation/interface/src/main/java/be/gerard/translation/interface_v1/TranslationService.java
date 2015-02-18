package be.gerard.translation.interface_v1;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.general.interface_v1.model.UserSession;
import be.gerard.translation.interface_v1.enums.Language;
import be.gerard.translation.interface_v1.model.Translation;

/**
 *
 * @author bartgerard
 */
public interface TranslationService {
    
    String ping();
    
    Translation saveOrUpdate(final Translation translation, final UserSession session) throws ServiceException;
    
    void delete(final Translation translation, final UserSession session);
    
    Translation get(final String application, final Language language, final String key);
    
}
