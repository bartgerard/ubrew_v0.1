package be.gerard.core.user.service.dao;

import be.gerard.core.user.service.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserDao
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface UserDao extends JpaRepository<UserRecord, String> {

    UserRecord findByUsername(String username);

}
