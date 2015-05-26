package be.gerard.general.service;

import be.gerard.core.interface_v1.exception_v1.ServiceException;
import be.gerard.general.interface_v1.BaseAuthenticationService;
import be.gerard.general.interface_v1.session.BaseSession;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.util.Assert;

/**
 * BaseAuthenticationServiceImpl
 *
 * @author bartgerard
 * @version 0.0.1
 *
 * @param <S> Session
 * @param <T> Token
 */
public abstract class BaseAuthenticationServiceImpl<S extends BaseSession, T extends Serializable> extends BaseServiceImpl implements BaseAuthenticationService<S, T> {

    private static final Logger LOGGER = LogManager.getLogger(BaseAuthenticationService.class.getName());

    private final Map<T, S> sessions = new HashMap<>();
    
    protected abstract Map<T, S> getSessions();

    protected abstract Long getTimeout();

    protected abstract boolean validateLogin(String key, String password);

    protected abstract S openSession(String key, String password);

    @Override
    public S login(String key, String password) throws ServiceException {
        if (!validateLogin(key, password)) {
            throw LOGGER.throwing(Level.DEBUG, new AuthenticationServiceException("InvalidCredentials"));
        }
        
        S session = openSession(key, password);

        Assert.notNull(session, "session is invalid [null]");
        
        getSessions().put((T) session.getToken(), session);
        return session;
    }

    // TODO SECURE!
    @Override
    public S findSession(final T token) {
        S session = getSessions().get(token);

        if (session == null) {
            throw LOGGER.throwing(Level.DEBUG, new AuthenticationServiceException("InvalidToken"));
        } else {
            return session;
        }
    }

    @Override
    public boolean validate(final S session) {
        if (session == null) {
            return false;
        }

        T token = (T) session.getToken();
        S lastSession = findSession(token);

        if (lastSession == null) {
            return false;
        }

        if (getTimeout() != null && LocalDateTime.now().isAfter(lastSession.getLastActivity().plusMinutes(getTimeout()))) {
            return false;
        }

        if (!Objects.equals(lastSession, session)) {
            return false;

        }

        lastSession.setLastActivity(LocalDateTime.now());
        return true;
    }

    @Override
    public void invalidate(final S session) {
        T token = (T) session.getToken();
        sessions.remove(token);
    }

}
