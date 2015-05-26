package be.gerard.common.merger;

import be.gerard.common.db.model.BaseRecord;
import be.gerard.common.to.BaseTo;

/**
 * DefaultCollectionMerger
 *
 * @author bartgerard
 * @version 0.0.1
 * 
 * @param <E> Entity
 * @param <T> TransportObject
 * @param <C> Context
 */
public abstract class DefaultCollectionMerger<E extends BaseRecord, T extends BaseTo, C extends MergeContext> extends CollectionMerger<E, T, C> {

    @Override
    protected Long getEntityId(E entity, C context) {
        return entity.getId();
//        final BeanWrapper entityWrapper = new BeanWrapperImpl(entity);
//        final Object id = entityWrapper.getPropertyValue("id");
//        return id;
    }

    @Override
    protected Long getClientId(T transportObject, C context) {
        return transportObject.getId();
//        final BeanWrapper transportObjectWrapper = new BeanWrapperImpl(transportObject);
//        final Object id = transportObjectWrapper.getPropertyValue("id");
//        return id;
    }

}
