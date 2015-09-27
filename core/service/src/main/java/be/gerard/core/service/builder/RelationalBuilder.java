package be.gerard.core.service.builder;

import be.gerard.common.db.model.BaseRecord;
import com.mysema.commons.lang.Assert;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * RelationalBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public abstract class RelationalBuilder<T extends BaseRecord> extends Builder<T> {

    private final Set<Builder> children = new HashSet<>();

    public RelationalBuilder(T record, BuilderContext builderContext) {
        super(record, builderContext);
    }

    protected <C extends Builder> C addChild(C child) {
        Assert.isTrue(children.add(child), "addChild() failed on child " + Objects.toString(child));
        return child;
    }

    @Override
    public Builder<T> build() {
        children.stream().filter(child -> !child.isExecuted()).forEach(be.gerard.core.service.builder.Builder::build);
        children.clear();

        return super.build();
    }
}
