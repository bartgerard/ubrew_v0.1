package be.gerard.admin.repositories;

import be.gerard.admin.entities.UserVO;
import be.gerard.admin.repositories.template.DAOTemplate;
import org.springframework.stereotype.Repository;

/**
 * User Data Access Object
 * 
 * @author bartgerard
 */
@Repository("userDAO")
public class UserDAO extends DAOTemplate<UserVO, Long> {
    
}
