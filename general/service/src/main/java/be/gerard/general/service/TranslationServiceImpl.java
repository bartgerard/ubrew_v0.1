package be.gerard.general.service;

import be.gerard.general.interface_v1.TranslationService;
import be.gerard.general.interface_v1.enums.Language;
import be.gerard.general.interface_v1.exception.TranslationServiceException;
import be.gerard.general.interface_v1.exception.error.TranslationServiceError;
import be.gerard.general.interface_v1.model.Translation;
import be.gerard.general.interface_v1.session.UserSession;
import be.gerard.general.service.dao.TranslationDAO;
import be.gerard.general.service.decoder.TranslationDecoder;
import be.gerard.general.service.model.TranslationRecord;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author bartgerard
 */
@Service
public class TranslationServiceImpl extends BaseServiceImpl implements TranslationService, Serializable {

    private static final Logger LOG = LogManager.getLogger(TranslationServiceImpl.class);

    @Autowired
    private TranslationDAO translationDAO;

    @Autowired
    private TranslationDecoder translationDecoder;

    @Override
    public Translation find(final Long id) {
        return translationDecoder.encode(translationDAO.find(id));
    }

    @Override
    public String get(final String application, final Language language, final String key) {
        Translation result = find(application, language, key);
        return result != null ? result.getValue() : null;
    }

    @Override
    public Translation find(final String application, final Language language, final String key) {
        return translationDecoder.encode(translationDAO.find(application, language.getCode(), key));
    }

    @Transactional(readOnly = false)
    @Override
    public List<Translation> saveOrUpdate(final List<Translation> translations, final UserSession session) throws TranslationServiceException {
        Assert.notEmpty(translations, "translations is invalid [EMPTY]");
        
        List<Translation> result = new ArrayList<>();
        
        for (Translation translation : translations) {
            result.add(saveOrUpdate(translation, session));
        }
        
        return result;
    }

    @Transactional(readOnly = false)
    @Override
    public Translation saveOrUpdate(final Translation translation, final UserSession session) throws TranslationServiceException {
        LOG.entry(translation, session);

        // VALIDATION
        Map<TranslationServiceError, String[]> errors = new HashMap<>();
        //validateTranslation(translation, null);

//        if (!authenticationService.validate(session)) {
//            errors.put(TranslationServiceError.SESSION_INVALID, new String[]{});
//        }

        if (!errors.isEmpty()) {
            throw LOG.throwing(Level.DEBUG, new TranslationServiceException("translation", errors));
        }

        Assert.notNull(session.getUser(), "Session was validated by the userService");
        Assert.hasLength(session.getUser().getUsername());

        TranslationRecord translationRecord = translationDAO.find(translation.getApplication(), translation.getLanguage(), translation.getKey());

        if (translationRecord == null) {
            translationRecord = translationDAO.saveOrUpdate(new TranslationRecord(translation.getApplication(), translation.getLanguage(), translation.getKey(), translation.getValue()));
        } else {
            translationRecord.setValue(translation.getValue());
            translationDAO.saveOrUpdate(translationRecord);
        }

        return LOG.exit(translationDecoder.encode(translationRecord));
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(final Translation translation, final UserSession session) {
        LOG.entry(translation, session);

        TranslationRecord translationRecord = translationDAO.find(translation.getId());

        // VALIDATE NULL?
        translationDAO.delete(translationRecord);
    }

    @Override
    public List<Translation> findAll() {
        return translationDecoder.encode(translationDAO.findAll());
    }

    @Override
    public List<Translation> findAll(final String application) {
        return translationDecoder.encode(translationDAO.findAll(application));
    }

    @Override
    public List<String> findAllSupportedLanguages() {
        return translationDAO.findAllSupportedLanguages();
    }

}
