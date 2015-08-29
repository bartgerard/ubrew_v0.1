package be.gerard.core.service.builder;

import be.gerard.core.service.model.PrivilegeRecord;

/**
 * PrivilegeBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class PrivilegeBuilder extends Builder<PrivilegeRecord> {

    public PrivilegeBuilder(PrivilegeRecord privilegeRecord, BuilderContext builderContext) {
        super(privilegeRecord, builderContext);
    }

    @Override
    public void save() {
        getBuilderContext().save(getRecord());
    }

}
