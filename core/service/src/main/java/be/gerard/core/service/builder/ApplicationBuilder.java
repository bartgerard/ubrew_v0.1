package be.gerard.core.service.builder;

import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.PropertyGroupRecord;
import be.gerard.core.service.model.TranslationGroupRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * ApplicationBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class ApplicationBuilder extends Builder<ApplicationRecord> {

    private final List<PropertyGroupRecord> propertyGroups = new ArrayList<>();

    private final List<TranslationGroupRecord> translationGroups = new ArrayList<>();

    ApplicationBuilder(ApplicationRecord applicationRecord, BuilderContext builderContext) {
        super(applicationRecord, builderContext);
    }

    public PropertyGroupBuilder propertyGroup(String key) {
        PropertyGroupRecord propertyGroup = getRecord().findPropertyGroup(key);

        if (propertyGroup == null) {
            propertyGroup = getBuilderContext().getOrProperyGroup(key);
        }

        if (!propertyGroups.contains(propertyGroup)) {
            propertyGroups.add(propertyGroup);
        }

        return new PropertyGroupBuilder(propertyGroup, getBuilderContext());
    }

    public TranslationGroupBuilder translationGroup(String key) {
        TranslationGroupRecord translationGroup = getRecord().findTranslationGroup(key);

        if (translationGroup == null) {
            translationGroup = getBuilderContext().getOrTranslationGroup(key);
        }

        if (!translationGroups.contains(translationGroup)) {
            translationGroups.add(translationGroup);
        }

        return new TranslationGroupBuilder(translationGroup, getBuilderContext());
    }

    public ApplicationBuilder build() {
        getRecord().getPropertyGroups().clear();

        for (PropertyGroupRecord propertyGroup : propertyGroups) {
            getRecord().getPropertyGroups().add(propertyGroup);
        }

        return this;
    }

}
