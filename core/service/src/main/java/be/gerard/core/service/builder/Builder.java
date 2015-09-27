package be.gerard.core.service.builder;

import be.gerard.common.db.model.BaseRecord;
import com.mysema.commons.lang.Assert;

import java.util.Objects;

/**
 * Builder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public abstract class Builder<T extends BaseRecord> {

    private boolean executed = Boolean.FALSE;

    private final T record;

    private final BuilderContext builderContext;

    public Builder(T record, BuilderContext builderContext) {
        this.record = record;
        this.builderContext = builderContext;
    }

    public boolean isExecuted() {
        return executed;
    }

    protected T getRecord() {
        return record;
    }

    protected BuilderContext getBuilderContext() {
        return builderContext;
    }

    public Builder<T> build() {
        Assert.isFalse(executed, "build() was already executed on " + Objects.toString(record));
        executed = Boolean.TRUE;
        return this;
    }

    public abstract void save();

    @Override
    public String toString() {
        return Objects.toString(getRecord());
    }

}
