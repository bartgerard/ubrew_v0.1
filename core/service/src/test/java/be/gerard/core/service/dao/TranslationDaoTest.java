package be.gerard.core.service.dao;

import be.gerard.core.interface_v1.enums.TranslationType;
import be.gerard.core.service.BaseTest;
import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.TranslationGroupMetaRecord;
import be.gerard.core.service.model.TranslationGroupRecord;
import be.gerard.core.service.model.TranslationRecord;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * PropertyDaoTest
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class TranslationDaoTest extends BaseTest {

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private CustomTranslationDao customTranslationDao;

    @Test
    public void test() {
        TranslationGroupRecord tg1 = new TranslationGroupRecord("test1");
        tg1.getTranslations().add(new TranslationRecord("key1", "valueA", "nl", null, TranslationType.FIELD));
        tg1.getTranslations().add(new TranslationRecord("key1", "valueC", "fr", null, TranslationType.FIELD));
        tg1.getTranslations().add(new TranslationRecord("key1", "valueE", "nl", "pref1", TranslationType.FIELD));

        TranslationGroupRecord tg2 = new TranslationGroupRecord("test2");
        tg2.getTranslations().add(new TranslationRecord("key1", "valueB", "nl", null, TranslationType.FIELD));
        tg2.getTranslations().add(new TranslationRecord("key1", "valueD", "fr", null, TranslationType.FIELD));
        tg2.getTranslations().add(new TranslationRecord("key1", "valueF", "nl", "pref1", TranslationType.FIELD));

        ApplicationRecord applicationRecord = new ApplicationRecord("app1");
        applicationRecord.getTranslationGroups().add(new TranslationGroupMetaRecord(tg1));
        applicationRecord.getTranslationGroups().add(new TranslationGroupMetaRecord(tg2));

        applicationDao.saveAndFlush(applicationRecord);

        // TESTS

        Optional<String> value1 = customTranslationDao.findByAppAndPrefixAndKeyAndLanguage("app1", null, "key1", "nl");

        Assert.assertTrue(value1.isPresent());
        Assert.assertEquals("valueA", value1.get());

        Optional<String> value2 = customTranslationDao.findByAppAndPrefixAndKeyAndLanguage("app1", null, "key1", "fr");

        Assert.assertTrue(value2.isPresent());
        Assert.assertEquals("valueC", value2.get());

        Optional<String> value3 = customTranslationDao.findByAppAndPrefixAndKeyAndLanguage("app1", "pref1", "key1", "nl");

        Assert.assertTrue(value3.isPresent());
        Assert.assertEquals("valueE", value3.get());

        Assert.assertFalse(customTranslationDao.findByAppAndPrefixAndKeyAndLanguage("app0", null, "key1", "nl").isPresent());
        Assert.assertFalse(customTranslationDao.findByAppAndPrefixAndKeyAndLanguage("app1", "pref0", "key1", "nl").isPresent());
    }

}
