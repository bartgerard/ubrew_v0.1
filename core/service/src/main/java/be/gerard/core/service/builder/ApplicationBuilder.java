package be.gerard.core.service.builder;

import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.PropertyGroupRecord;
import be.gerard.core.service.model.TranslationGroupMetaRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * ApplicationBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class ApplicationBuilder extends RelationalBuilder<ApplicationRecord> {

    private final List<PropertyGroupRecord> propertyGroups = new ArrayList<>();

    private final List<TranslationGroupMetaRecord> translationGroups = new ArrayList<>();

    ApplicationBuilder(ApplicationRecord applicationRecord, BuilderContext builderContext) {
        super(applicationRecord, builderContext);
    }

    public PropertyGroupBuilder propertyGroup(String key) {
        PropertyGroupRecord propertyGroup = getRecord().findPropertyGroup(key);

        if (propertyGroup == null) {
            propertyGroup = getBuilderContext().getOrCreateProperyGroup(key);
        }

        if (!propertyGroups.contains(propertyGroup)) {
            propertyGroups.add(propertyGroup);
        }

        return addChild(new PropertyGroupBuilder(propertyGroup, getBuilderContext(), this));
    }

    public TranslationGroupBuilder translationGroup(String key) {
        TranslationGroupMetaRecord translationGroupMeta = getRecord().findTranslationGroupMeta(key);

        if (translationGroupMeta == null) {
            translationGroupMeta = new TranslationGroupMetaRecord(getBuilderContext().getOrCreateTranslationGroup(key));
        }

        if (!translationGroups.contains(translationGroupMeta)) {
            translationGroups.add(translationGroupMeta);
        }

        return addChild(new TranslationGroupBuilder(translationGroupMeta.getGroup(), getBuilderContext(), this));
    }

    @Override
    public ApplicationBuilder build() {
        super.build();

        getRecord().getPropertyGroups().clear();

        for (PropertyGroupRecord propertyGroup : propertyGroups) {
            getRecord().getPropertyGroups().add(propertyGroup);
        }

        getRecord().getTranslationGroups().clear();

        for (TranslationGroupMetaRecord translationGroup : translationGroups) {
            getRecord().getTranslationGroups().add(translationGroup);
        }

        return this;
    }

    @Override
    public void save() {
        getBuilderContext().save(getRecord());
    }

}
