package be.gerard.common.merger;

import be.gerard.common.db.model.BaseRecord;
import be.gerard.common.to.BaseTo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * CollectionMerger
 *
 * @author bartgerard
 * @version 0.0.1
 * @param <E> Entity
 * @param <T> TransportObject
 * @param <C> Context
 */
public abstract class CollectionMerger<E extends BaseRecord, T extends BaseTo, C extends MergeContext> {

    public void mergeCollection(Collection<E> entities, Collection<T> transportObjects, C context) {
        final Set<E> updatedEntities = new HashSet<>();
        final Set<E> newEntities = new HashSet<>();

        if (transportObjects != null) {
            transportObjects.stream().forEach((transportObject) -> {
                final Object clientId = getClientId(transportObject, context);

                // Search matching entity
                E entity = findEntity(entities, clientId, context);

                if (clientId == null || entity == null) {
                    final E newEntity = handleNew(transportObject, context);

                    if (newEntity != null) {
                        newEntities.add(newEntity);
                    }
                } else {
                    handleUpdate(entity, transportObject, context);
                    updatedEntities.add(entity);
                }
            });
        }

        // Remove all elements that were not on the transportObject
        updatedEntities.stream().filter((updatedEntity) -> (!entities.contains(updatedEntity))).filter((updatedEntity) -> (handleRemove(updatedEntity, context))).forEach((updatedEntity) -> {
            entities.remove(updatedEntity);
        });

        // Add all new elements
        entities.addAll(newEntities);
    }

    private E findEntity(Collection<E> entities, Object clientId, C context) {
        if (clientId != null) {
            for (E entity : entities) {
                if (Objects.equals(clientId, getEntityId(entity, context))) {
                    return entity;
                }
            }
        }

        return null;
    }

    protected abstract E handleNew(T transportObject, C context);

    protected void handleUpdate(E entity, T transportObject, C context) {
        // Default
    }

    protected boolean handleRemove(E entity, C context) {
        return true;
    }

    protected abstract Object getEntityId(E entity, C context);

    protected abstract Object getClientId(T transportObject, C context);

}
