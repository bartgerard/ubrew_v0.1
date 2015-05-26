package be.gerard.core.service;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.core.interface_v1.ApplicationService;
import be.gerard.core.interface_v1.AuthenticationService;
import be.gerard.core.interface_v1.UserService;
import be.gerard.core.interface_v1.model.User;
import be.gerard.core.interface_v1.session.AppSession;
import be.gerard.core.interface_v1.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * AuthenticationServiceImpl
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final Map<UUID, AppSession> sessions = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;

    @Override
    public UserSession login(String username, String password) {
        User user = userService.findByUsernameAndPassword(username, password);
        UserSession userSession = new UserSession(user);
        return userSession;
    }

    @Override
    public void logout(UUID token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserSession findSession(UUID token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppSession register(String key, String password) throws ServiceException {
        // TODO validation
        //applicationService;

        AppSession appSession = new AppSession();
        sessions.put(appSession.getToken(), appSession);
        return appSession;
    }

    @Override
    public Map<String, String> retrieveProperties(UUID token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
