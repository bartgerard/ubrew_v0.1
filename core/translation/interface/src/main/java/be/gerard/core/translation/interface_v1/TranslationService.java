package be.gerard.core.translation.interface_v1;

import be.gerard.common.annotation.validation.Validate;
import be.gerard.core.translation.interface_v1.enums.Language;
import be.gerard.core.translation.interface_v1.model.Translation;

import java.util.List;

/**
 * @author bartgerard
 */
public interface TranslationService {

    Translation find(Long id);

    Translation find(String application, Language language, String key);

    Translation save(@Validate Translation translation);

    List<Translation> save(@Validate List<Translation> translations);

    void remove(Translation translation);

    List<Translation> findAll();

    List<Translation> findAll(String application);

    List<String> findAllSupportedLanguages();

}
