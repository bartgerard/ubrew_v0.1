package be.gerard.core.service.dao;

import be.gerard.core.service.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * UserDao
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface UserDao extends JpaRepository<UserRecord, UUID> {

    UserRecord findByUsername(String username);

}
