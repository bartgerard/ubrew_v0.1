package be.gerard.core.service.builder;

import be.gerard.core.interface_v1.enums.PropertyType;
import be.gerard.core.service.model.PropertyGroupRecord;
import be.gerard.core.service.model.PropertyRecord;

import java.util.HashSet;
import java.util.Set;

/**
 * PropertyGroupBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class PropertyGroupBuilder extends ChildBuilder<PropertyGroupRecord, ApplicationBuilder> {

    private final Set<PropertyRecord> properties = new HashSet<>();

    public PropertyGroupBuilder(
            PropertyGroupRecord record, BuilderContext builderContext, ApplicationBuilder parent
    ) {
        super(record, builderContext, parent);
    }

    public PropertyGroupBuilder property(String key, PropertyType type, String value) {
        PropertyRecord property = getRecord().findByKey(key);

        if (property == null) {
            property = new PropertyRecord(key);
        }

        if (!properties.contains(property)) {
            properties.add(property);
        }

        property.setType(type);
        property.setValue(value);

        return this;
    }

    @Override
    public PropertyGroupBuilder build() {
        super.build();

        getRecord().getProperties().clear();

        for (PropertyRecord property : properties) {
            getRecord().getProperties().add(property);
        }

        return this;
    }

    @Override
    public void save() {
    }

}
