package be.gerard.core.service;

import be.gerard.core.interface_v1.TranslationService;
import be.gerard.core.interface_v1.enums.Language;
import be.gerard.core.interface_v1.enums.TranslationType;
import be.gerard.core.interface_v1.model.Translation;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * TranslationServiceTest
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-02-03 20:32
 */
public class TranslationServiceTest extends BaseTest {

    @Autowired
    private TranslationService translationService;

    private final String application = "ubrew";
    private final String language = "nl";
    private final String prefix = "common";
    private final TranslationType type = TranslationType.FIELD;
    private final String key = "key";
    private final String value = "Sleutel";

    @Ignore // TODO
    @Test
    public void crudTest() {
        Translation translation = new Translation(application, language, prefix, type, key, value);
        translationService.save(translation);

        Translation translation1 = translationService.find(application, prefix, Language.NL, key);
        Assert.assertNotNull(translation1);

        List<Translation> translationList1 = translationService.findAll();
        Assert.assertFalse(translationList1.isEmpty());

        List<Translation> translationList2 = translationService.findAll(application);
        Assert.assertFalse(translationList2.isEmpty());
    }

//    @Test
//    public void multipleCrudTest() {
//        Translation translation1 = new Translation(application, language, "a", "a");
//        Translation translation2 = new Translation(application, language, null, "b");
//
//        translationService.save(Arrays.asList(translation1, translation2));
//
//        List<Translation> translationList2 = translationService.findAll(application);
//        Assert.assertTrue(translationList2.size() == 2);
//    }

}
