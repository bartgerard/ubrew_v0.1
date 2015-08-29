package be.gerard.common.security;

import be.gerard.core.interface_v1.session.UserSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * CommonAuthenticationToken
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class CommonAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public CommonAuthenticationToken(String username, UserSession userSession, Collection<? extends GrantedAuthority> authorities) {
        super(username, userSession, authorities);
    }

    public UserSession getSession() {
        return (UserSession) getCredentials();
    }

}
