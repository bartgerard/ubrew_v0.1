package be.gerard.core.service.converter;

import be.gerard.common.converter.GenericPropertyConverter;
import be.gerard.core.interface_v1.model.Role;
import be.gerard.core.service.model.PrivilegeRecord;
import be.gerard.core.service.model.RoleRecord;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

/**
 * RoleConverter
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Component
public class RoleConverter extends GenericPropertyConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(RoleRecord.class, Role.class));
    }

    @Override
    public Role convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        RoleRecord roleRecord = (RoleRecord) source;
        Role role = (Role) super.convert(roleRecord, sourceType, targetType);

        for (PrivilegeRecord privilegeRecord : roleRecord.getPrivileges()) {
            role.getPrivileges().add(privilegeRecord.getName());
        }

        return role;
    }
}
