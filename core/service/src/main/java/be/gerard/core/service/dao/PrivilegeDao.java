package be.gerard.core.service.dao;

import be.gerard.core.service.model.PrivilegeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PrivilegeDao
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface PrivilegeDao extends JpaRepository<PrivilegeRecord, Long> {

    PrivilegeRecord findByName(String name);

}
