package be.gerard.core.application.service.merger;

import be.gerard.common.merger.DefaultCollectionMerger;
import be.gerard.common.merger.MergeContext;
import be.gerard.core.application.interface_v1.model.Property;
import be.gerard.core.application.service.dao.PropertyDao;
import be.gerard.core.application.service.model.PropertyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * PropertyMerger
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-10 22:36
 */
@Component
public class PropertyMerger extends DefaultCollectionMerger<PropertyRecord, Property, MergeContext> {

    @Autowired
    private PropertyDao propertyDao;

    @Override
    protected PropertyRecord handleNew(Property property, MergeContext context) {
        // TODO Improve by reusing commong properties
        return new PropertyRecord(property.getKey(), property.getGroup(), property.getValue());
    }

    @Override
    protected void handleUpdate(PropertyRecord propertyRecord, Property property, MergeContext context) {
        propertyRecord.setValue(property.getValue());
    }
}
