package be.gerard.core.service.builder;

import be.gerard.common.db.model.BaseRecord;

/**
 * Builder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public abstract class Builder<T extends BaseRecord> {

    private final T record;

    private final BuilderContext builderContext;

    public Builder(T record, BuilderContext builderContext) {
        this.record = record;
        this.builderContext = builderContext;
    }

    protected T getRecord() {
        return record;
    }

    protected BuilderContext getBuilderContext() {
        return builderContext;
    }

    public abstract void save();

}
