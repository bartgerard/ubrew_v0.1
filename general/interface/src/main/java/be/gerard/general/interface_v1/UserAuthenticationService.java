package be.gerard.general.interface_v1;

import be.gerard.core.interface_v1.exception_v1.ServiceException;
import be.gerard.general.interface_v1.session.UserSession;
import java.util.UUID;

/**
 * BaseAuthenticationService
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface UserAuthenticationService extends BaseService {

    UserSession login(String username, String password) throws ServiceException;
    
    void logout(UUID token);
    
    UserSession findSession(UUID token);
    
}
