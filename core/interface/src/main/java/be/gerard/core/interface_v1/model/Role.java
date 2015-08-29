package be.gerard.core.interface_v1.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Role
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class Role {

    private String name;

    private final Set<String> privileges = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPrivileges() {
        return privileges;
    }

}
