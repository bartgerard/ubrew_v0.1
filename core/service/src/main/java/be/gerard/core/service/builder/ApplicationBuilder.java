package be.gerard.core.service.builder;

import be.gerard.core.service.model.ApplicationRecord;

/**
 * ApplicationBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class ApplicationBuilder {

    private final ApplicationRecord applicationRecord;

    private final BuilderContext builderContext;

    ApplicationBuilder(ApplicationRecord applicationRecord, BuilderContext builderContext) {
        this.applicationRecord = applicationRecord;
        this.builderContext = builderContext;
    }

    public void save() {
        builderContext.save(applicationRecord);
    }

}
