package be.gerard.core.service.builder;

import be.gerard.core.service.model.PrivilegeRecord;
import be.gerard.core.service.model.RoleRecord;

import java.util.HashSet;
import java.util.Set;

/**
 * RoleBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class RoleBuilder extends Builder<RoleRecord> {

    private final Set<PrivilegeRecord> privileges = new HashSet<>();

    public RoleBuilder(RoleRecord roleRecord, BuilderContext builderContext) {
        super(roleRecord, builderContext);
    }

    public RoleBuilder addPrivilege(String privilegeName) {
        privileges.add(getBuilderContext().getOrCreatePrivilege(privilegeName));
        return this;
    }

    public RoleBuilder build() {
        getRecord().getPrivileges().clear();
        getRecord().getPrivileges().addAll(privileges);
        return this;
    }

    @Override
    public void save() {
        getBuilderContext().save(getRecord());
    }

}
