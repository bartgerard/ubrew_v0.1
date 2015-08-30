package be.gerard.core.service.dao;

import be.gerard.core.service.model.RoleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleDao
 *
 * @author bartgerard
 * @version v0.0.1
 */
public interface RoleDao extends JpaRepository<RoleRecord, Long> {

    RoleRecord findByName(String name);

}
