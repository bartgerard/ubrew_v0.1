package be.gerard.core.service;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.core.interface_v1.AuthenticationService;
import be.gerard.core.interface_v1.UserService;
import be.gerard.core.interface_v1.model.User;
import be.gerard.core.interface_v1.session.AppSession;
import be.gerard.core.interface_v1.session.UserSession;
import be.gerard.core.service.dao.ApplicationInstanceDao;
import be.gerard.core.service.model.ApplicationInstanceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final Map<UUID, AppSession> appSessions = new HashMap<>();

    private final Map<UUID, UserSession> userSessions = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationInstanceDao applicationInstanceDao;

    @Override
    public UserSession login(String username, String password) {
        User user = userService.findByUsernameAndPassword(username, password);
        UserSession userSession = new UserSession(user);
        userSessions.put(userSession.getToken(), userSession);
        return userSession;
    }

    @Override
    public void logout(UUID token) {
        userSessions.remove(token);
    }

    @Override
    public UserSession findSession(UUID token) {
        return userSessions.get(token);
    }

    @Override
    public AppSession register(String appKey, String reference, String password) throws ServiceException {
        // TODO validation VALID PASSWORD?
        ApplicationInstanceRecord applicationInstanceRecord = applicationInstanceDao.findByApplicationAndReference(appKey, reference);

        AppSession appSession = new AppSession();

        appSession.getProperties().putAll(applicationInstanceRecord.getApplication().findProperties());

        //appSession.setApplication(conversionService.convert(applicationInstanceRecord.getApplication(), Application.class));
        appSessions.put(appSession.getToken(), appSession);
        return appSession;
    }

}
