package be.gerard.general.service.security;

import be.gerard.general.interface_v1.UserAuthenticationService;
import be.gerard.general.interface_v1.session.UserSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author bartgerard
 */
@Component
public class ServiceAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserAuthenticationService authenticationService;

    @Override
    public Authentication authenticate(final Authentication a) throws AuthenticationException {
        String username = a.getName();
        String password = a.getCredentials().toString();

        UserSession userSession = authenticationService.login(username, password);
        //user authenticated
        if (userSession == null) {
            return null;
        } else {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
