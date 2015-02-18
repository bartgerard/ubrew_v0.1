package be.gerard.general.interface_v1;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.general.interface_v1.session.AppSession;
import java.util.Map;
import java.util.UUID;

/**
 * BaseAuthenticationService
 *
 * @author bartgerard
 * @version 0.0.1
 */
public interface ApplicationAuthenticationService extends BaseService {

    AppSession register(String key, String password) throws ServiceException;

    Map<String, String> retrieveProperties(UUID token);

}
