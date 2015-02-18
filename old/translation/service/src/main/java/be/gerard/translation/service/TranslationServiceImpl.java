package be.gerard.translation.service;

import be.gerard.general.interface_v1.UserService;
import be.gerard.general.interface_v1.model.UserSession;
import be.gerard.translation.interface_v1.TranslationService;
import be.gerard.translation.interface_v1.enums.Language;
import be.gerard.translation.interface_v1.exception.TranslationServiceException;
import be.gerard.translation.interface_v1.exception.error.TranslationServiceError;
import be.gerard.translation.interface_v1.model.Translation;
import be.gerard.translation.service.dao.TranslationDAO;
import be.gerard.translation.service.decoder.TranslationDecoder;
import be.gerard.translation.service.model.TranslationRecord;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 *
 * @author bartgerard
 */
@Service
public class TranslationServiceImpl  implements TranslationService {

    private static final Logger logger = LogManager.getLogger(TranslationServiceImpl.class.getName());

    @Autowired(required = true)
    private TranslationDAO translationDAO;

    @Autowired(required = true)
    private TranslationDecoder translationDecoder;

    @Autowired(required = true)
    private UserService userService;

    @Value("${be.gerard.translation.language.pattern}")
    private String languagePattern;

    @Override
    public String ping() {
        //"You have reached the voicemail of uBrew. Please leave a message after the beep. *Beep* Hi %s here! I forgot to thank you for your wonderful beer! Keep up the good work!"
        return String.format("pong() %s", LocalDateTime.now());
    }

    @Override
    public Translation saveOrUpdate(Translation translation, UserSession session) throws TranslationServiceException {
        logger.entry(translation, session);

        // VALIDATION
        Map<TranslationServiceError, String[]> errors = new HashMap<>();
        validateTranslation(translation, null);

        if (!userService.validate(session)) {
            errors.put(TranslationServiceError.SessionInvalid, new String[]{});
        }

        if (!errors.isEmpty()) {
            throw logger.throwing(Level.DEBUG, new TranslationServiceException("translation", errors));
        }
        
        Assert.notNull(session.getUser(), "Session was validated by the userService");
        Assert.hasLength(session.getUser().getUsername());

        TranslationRecord translationRecord = translationDAO.get(translation.getApplication(), translation.getLanguage(), translation.getKey());

        if (translationRecord == null) {
            translationRecord = translationDAO.saveOrUpdate(new TranslationRecord(translation.getApplication(), translation.getLanguage(), translation.getKey(), translation.getValue(), session.getUser().getUsername()));
        } else {
            translationRecord.setValue(translation.getValue());
            translationRecord.setChangedBy(session.getUser().getUsername());
            translationDAO.saveOrUpdate(translationRecord);
        }

        return logger.exit(translationDecoder.encode(translationRecord));
    }

    private void validateTranslation(final Translation translation, final Map<TranslationServiceError, String[]> errors) {
        if (translation == null) {
            errors.put(TranslationServiceError.TranslationNull, new String[]{});
        } else {
            if (!StringUtils.hasText(translation.getApplication())) {
                errors.put(TranslationServiceError.ApplicationInvalid, new String[]{translation.getApplication()});
            }
//            if (!StringUtils.hasText(translation.getLanguage()) || !Pattern.matches(languagePattern, translation.getLanguage())) {
//                errors.put(TranslationServiceError.LanguageInvalid, new String[]{translation.getLanguage()});
//            }
            if (translation.getLanguage() == null || !Pattern.matches(languagePattern, translation.getLanguage())) {
                errors.put(TranslationServiceError.LanguageInvalid, new String[]{Objects.toString(translation.getLanguage())});
            }
            if (!StringUtils.hasText(translation.getKey())) {
                errors.put(TranslationServiceError.KeyInvalid, new String[]{translation.getKey()});
            }
            if (!StringUtils.hasText(translation.getValue())) {
                errors.put(TranslationServiceError.ValueInvalid, new String[]{translation.getValue()});
            }
        }
    }

    @Override
    public void delete(Translation translation, UserSession session) {
        logger.entry(translation, session);

        TranslationRecord translationRecord = translationDAO.get(translation.getId());

        // VALIDATE NULL?
        translationDAO.delete(translationRecord);
    }

    @Override
    public Translation get(String application, Language language, String key) {
        logger.entry(application, language, key);
        return logger.exit(translationDecoder.encode(translationDAO.get(application, language.getCode(), key)));
    }

}
