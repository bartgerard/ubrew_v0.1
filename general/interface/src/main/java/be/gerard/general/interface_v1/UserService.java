package be.gerard.general.interface_v1;

import be.gerard.core.interface_v1.annotation.validation.Validation;
import be.gerard.core.interface_v1.exception_v1.ServiceException;
import be.gerard.general.interface_v1.model.UserDetail;
import java.util.List;

/**
 *
 * @author bartgerard
 */
public interface UserService extends BaseService {
    
    UserDetail find(Long id, String application);
    
    UserDetail saveOrUpdate(@Validation(validators = "user") UserDetail user, @Validation(validators = "password") String password, String application) throws ServiceException;
    
    int count();
    
    void remove(UserDetail user);
    
    List<UserDetail> findAll(String application);
    
}
