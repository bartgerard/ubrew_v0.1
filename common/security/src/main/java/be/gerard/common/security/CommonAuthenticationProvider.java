package be.gerard.common.security;

import be.gerard.core.interface_v1.AuthenticationService;
import be.gerard.core.interface_v1.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * CommonAuthenticationManager
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Component
public class CommonAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public CommonAuthenticationToken authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserSession userSession = authenticationService.login(username, password);

//        UserTO user;
//        try {
//            user = personManager.login(username, password);
//            SessionUtils.setConnectedUser(user);
//        } catch (CRBSClientException e) {
//             return null;
//        }
        //user authenticated
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("USER"));
        grantedAuths.add(new SimpleGrantedAuthority("ADMIN"));
        return new CommonAuthenticationToken(username, userSession, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
