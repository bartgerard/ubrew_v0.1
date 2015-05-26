package be.gerard.general.service;

import be.gerard.core.interface_v1.exception_v1.ServiceException;
import be.gerard.general.interface_v1.ApplicationAuthenticationService;
import be.gerard.general.interface_v1.session.AppSession;
import be.gerard.general.service.dao.ApplicationDAO;
import be.gerard.general.service.dao.ApplicationDetailDAO;
import be.gerard.general.service.model.ApplicationDetailRecord;
import be.gerard.general.service.session.ApplicationSession;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * ApplicationAuthenticationServiceImpl
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Service
public class ApplicationAuthenticationServiceImpl extends BaseAuthenticationServiceImpl<ApplicationSession, UUID>  implements ApplicationAuthenticationService {

    @Autowired
    private ApplicationSessions sessions;

    @Autowired
    private ApplicationDAO applicationDAO;

    @Autowired
    private ApplicationDetailDAO applicationDetailDAO;

    @Override
    protected Long getTimeout() {
        return null;
    }

    @Override
    protected Map<UUID, ApplicationSession> getSessions() {
        return sessions.getSessions();
    }

    @Override
    protected boolean validateLogin(String key, String password) {
        return applicationDAO.validateLogin(key, password);
    }

    @Override
    protected ApplicationSession openSession(String key, String password) {
        ApplicationDetailRecord applicationDetailRecord = applicationDetailDAO.findByKey(key);
        Assert.notNull(applicationDetailRecord, "applicationDetailRecord is invalid [null]");
        ApplicationSession session = new ApplicationSession(applicationDetailRecord);
        getSessions().put(session.getToken(), session);
        return session;
    }

    @Override
    public AppSession register(final String key, final String password) throws ServiceException {
        return new AppSession(login(key, password).getToken());
    }

    @Override
    public Map<String, String> retrieveProperties(final UUID token) {
        return findSession(token).getApplication().getProperties();
    }

}
