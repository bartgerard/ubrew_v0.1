package be.gerard.core.service.dao;

import be.gerard.core.service.model.RoleRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * RoleDao
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface RoleDao extends CrudRepository<RoleRecord, Long> {

    RoleRecord findByName(String name);

}
