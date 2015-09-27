package be.gerard.core.service.builder;

import be.gerard.core.service.model.TranslationGroupRecord;
import be.gerard.core.service.model.TranslationRecord;

import java.util.HashSet;
import java.util.Set;

/**
 * PropertyGroupBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class TranslationGroupBuilder extends ChildBuilder<TranslationGroupRecord, ApplicationBuilder> {

    private final Set<TranslationRecord> translations = new HashSet<>();

    public TranslationGroupBuilder(
            TranslationGroupRecord record, BuilderContext builderContext, ApplicationBuilder parent
    ) {
        super(record, builderContext, parent);
    }

    public TranslationBuilder translation(
            String key, String language, String prefix
    ) {
        TranslationRecord translation = getRecord().findByKeyAndLanguage(key, language);

        if (translation == null) {
            translation = new TranslationRecord();
            translation.setKey(key);
            translation.setLanguage(language);
        }

        if (!translations.contains(translation)) {
            translations.add(translation);
        }

        translation.setLanguage(language);
        translation.setPrefix(prefix);

        return new TranslationBuilder(translation, getBuilderContext(), this);
    }

    @Override
    public TranslationGroupBuilder build() {
        super.build();

        getRecord().getTranslations().clear();

        for (TranslationRecord translation : translations) {
            getRecord().getTranslations().add(translation);
        }

        return this;
    }

    @Override
    public void save() {
    }

}
