package be.gerard.general.service;

import be.gerard.common.authentication.util.AppSessionUtils;
import be.gerard.common.exception_v1.ServiceException;
import be.gerard.general.interface_v1.UserAuthenticationService;
import be.gerard.general.interface_v1.session.AppSession;
import be.gerard.general.interface_v1.session.UserSession;
import be.gerard.general.service.dao.UserDAO;
import be.gerard.general.service.dao.UserDetailDAO;
import be.gerard.general.service.decoder.UserDetailDecoder;
import be.gerard.general.service.model.UserDetailRecord;
import be.gerard.general.service.session.ApplicationSession;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * AuthenticationServiceImpl
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Service
public class UserAuthenticationServiceImpl extends BaseAuthenticationServiceImpl<UserSession, UUID> implements UserAuthenticationService {

    @Value(value = "${be.gerard.general.user.session.timeout}")
    private long timeout;

    @Autowired
    private ApplicationSessions sessions;

    @Override
    public Long getTimeout() {
        return timeout;
    }

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserDetailDAO userDetailDAO;

    @Autowired
    private UserDetailDecoder userDetailDecoder;

    @Override
    protected Map<UUID, UserSession> getSessions() {
        AppSession appSession = AppSessionUtils.getAppSession();
        Assert.notNull(appSession, "appSession is invalid [null]");
        ApplicationSession applicationSession = this.sessions.getSessions().get(appSession.getToken());
        Assert.notNull(applicationSession, "applicationSession is invalid [null]");
        return applicationSession.getSessions();
    }

    @Override
    protected boolean validateLogin(final String username, final String password) {
        return userDAO.validateLogin(username, password);
    }

    @Override
    public UserSession openSession(final String username, final String password) throws ServiceException {
        UserDetailRecord userRecord = userDetailDAO.getByUsername(username);
        Assert.notNull(userRecord, "userRecord is invalid [null]");
        return new UserSession(userDetailDecoder.encode(userRecord, null));
    }

    @Override
    public void logout(UUID token) {
        getSessions().remove(token);
    }

}
