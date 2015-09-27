package be.gerard.core.service;

import be.gerard.core.interface_v1.TranslationService;
import be.gerard.core.interface_v1.enums.Language;
import be.gerard.core.interface_v1.model.Translation;
import be.gerard.core.service.dao.CustomTranslationDao;
import be.gerard.core.service.dao.TranslationDao;
import be.gerard.core.service.dao.TranslationGroupDao;
import be.gerard.core.service.model.TranslationGroupRecord;
import be.gerard.core.service.model.TranslationRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author bartgerard
 */
@Service
@javax.transaction.Transactional
public class TranslationServiceImpl implements TranslationService {

    private static final Logger LOG = LogManager.getLogger(TranslationServiceImpl.class);

    @Autowired
    private TranslationDao translationDao;

    @Autowired
    private CustomTranslationDao customTranslationDao;

    @Autowired
    private TranslationGroupDao translationGroupDao;

    @Autowired
    private ConversionService conversionService;

    @Override
    public String get(String application, String prefix, Language language, String key) {
        Optional<String> translation = customTranslationDao.findByAppAndPrefixAndKeyAndLanguage(application, prefix, key, language.getCode());

        return translation.isPresent() ? translation.get() : null;
    }

    @Override
    public Translation find(final Long id) {
        return conversionService.convert(translationDao.findOne(id), Translation.class);
    }

    @Override
    public Translation find(
            final String application,
            final String prefix,
            final Language language,
            final String key
    ) {
        return null; // TODO
        //return conversionService.convert(translationDao.findByApplicationAndPrefixAndLanguageAndKey(application, prefix, language.getCode(), key), Translation.class);
    }

    @Transactional(readOnly = false)
    @Override
    public List<Translation> save(final List<Translation> translations) {
        Assert.notEmpty(translations, "translations is invalid [EMPTY]");

        return translations.stream().map(this::save).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    @Override
    public Translation save(final Translation translation) {
        LOG.entry(translation);

        TranslationGroupRecord translationGroupRecord = translationGroupDao.findByKey(translation.getGroup());

        if (translationGroupRecord == null) {
            return null;
        }

        TranslationRecord translationRecord = translationGroupRecord.findByKeyAndLanguage(translation.getKey(), translation.getLanguage());

//        TranslationRecord translationRecord = translationDao.findByApplicationAndPrefixAndLanguageAndKey(
//                translation.getGroup(), translation.getPrefix(), translation.getLanguage(), translation.getKey()
//        );

        if (translationRecord == null) {
            translationRecord = new TranslationRecord();
            translationRecord.setPrefix(translation.getPrefix());
            translationRecord.setType(translation.getType());
            translationRecord.setLanguage(translation.getLanguage());
            translationRecord.setKey(translation.getKey());
            translationRecord.setValue(translation.getValue());

            translationGroupRecord.getTranslations().add(translationRecord);
        }

        translationRecord.setValue(translation.getValue());
        translationGroupDao.save(translationGroupRecord);

        return LOG.exit(conversionService.convert(translationRecord, Translation.class));
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(final Translation translation) {
        LOG.entry(translation);

        TranslationRecord translationRecord = translationDao.findOne(translation.getId());

        // VALIDATE NULL?
        translationDao.delete(translationRecord);
    }

    @Override
    public List<Translation> findAll() {
        List<Translation> results = new ArrayList<>();

        for (TranslationRecord translationRecord : translationDao.findAll()) {
            results.add(conversionService.convert(translationRecord, Translation.class));
        }

        return results;
    }

    @Override
    public List<Translation> findAll(final String application) {
        List<Translation> results = new ArrayList<>();

//        for (TranslationRecord translationRecord : translationDao.findByApplication(application)) {
//            results.add(conversionService.convert(translationRecord, Translation.class));
//        }

        return results;
    }

    @Override
    public List<String> findAllSupportedLanguages() {
        return new ArrayList<>();
        //    return translationDao.findAllSupportedLanguages();
    }

}
