package be.gerard.general.interface_v1;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.general.interface_v1.session.BaseSession;
import java.io.Serializable;

/**
 * BaseAuthenticationService
 *
 * @author bartgerard
 * @version 0.0.1
 * 
 * @param <S> Session
 * @param <T> Token
 */
public interface BaseAuthenticationService<S extends BaseSession, T extends Serializable> {

    S login(String name, String password) throws ServiceException;

    S findSession(T token);

    boolean validate(S session);

    void invalidate(S session);

}
