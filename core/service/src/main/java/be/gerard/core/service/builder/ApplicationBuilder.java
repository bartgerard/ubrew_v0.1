package be.gerard.core.service.builder;

import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.PropertyRecord;

import java.util.HashSet;
import java.util.Set;

/**
 * ApplicationBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class ApplicationBuilder extends Builder<ApplicationRecord> {

    private final Set<PropertyRecord> properties = new HashSet<>();

    ApplicationBuilder(ApplicationRecord applicationRecord, BuilderContext builderContext) {
        super(applicationRecord, builderContext);
    }

    public ApplicationBuilder property(String key, String group, String value) {
        PropertyRecord property = getRecord().findProperty(key);

        if (property == null) {
            property = new PropertyRecord(key);
        }

        if (!properties.contains(property)) {
            properties.add(property);
        }

        property.setGroup(group);
        property.setValue(value);

        return this;
    }

    public ApplicationBuilder build() {
        getRecord().clearProperties();

        for (PropertyRecord property : properties) {
            getRecord().addProperty(property);
        }

        return this;
    }

}
