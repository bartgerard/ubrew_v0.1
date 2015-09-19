package be.gerard.core.service.builder;

import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.PropertyGroupRecord;

import java.util.HashSet;
import java.util.Set;

/**
 * ApplicationBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class ApplicationBuilder extends Builder<ApplicationRecord> {

    private final Set<PropertyGroupRecord> propertyGroups = new HashSet<>();

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

    public ApplicationBuilder build() {
        getRecord().getPropertyGroups().clear();

        for (PropertyGroupRecord propertyGroup : propertyGroups) {
            getRecord().getPropertyGroups().add(propertyGroup);
        }

        return this;
    }

}
