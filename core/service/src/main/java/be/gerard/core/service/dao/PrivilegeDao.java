package be.gerard.core.service.dao;

import be.gerard.core.service.model.PrivilegeRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * PrivilegeDao
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface PrivilegeDao extends CrudRepository<PrivilegeRecord, Long> {

    PrivilegeRecord findByName(String name);

}
