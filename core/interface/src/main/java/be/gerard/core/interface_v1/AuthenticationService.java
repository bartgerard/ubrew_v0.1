package be.gerard.core.interface_v1;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.core.interface_v1.session.AppSession;
import be.gerard.core.interface_v1.session.UserSession;

import java.util.Map;
import java.util.UUID;

/**
 * AuthenticationService
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface AuthenticationService {

    UserSession login(String username, String password);

    void logout(UUID token);

    UserSession findSession(UUID token);

    AppSession register(String key, String password) throws ServiceException;

    Map<String, String> retrieveProperties(UUID token);

}
