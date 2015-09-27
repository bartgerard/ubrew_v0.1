package be.gerard.core.service.builder;

import be.gerard.common.db.model.BaseRecord;
import org.springframework.util.Assert;

/**
 * ChildBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public abstract class ChildBuilder<T extends BaseRecord, P extends Builder<? extends BaseRecord>> extends RelationalBuilder<T> {

    private final P parent;

    public ChildBuilder(T record, BuilderContext builderContext, P parent) {
        super(record, builderContext);
        this.parent = parent;
    }

    public P and() {
        Assert.notNull(parent, "parent is invalid [null]");
        return parent;
    }

    @Override
    public ChildBuilder<T, P> build() {
        super.build();
        return this;
    }

}
