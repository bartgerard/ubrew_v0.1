package be.gerard.core.web.controller;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.common.security.CommonAuthenticationProvider;
import be.gerard.common.security.CommonAuthenticationToken;
import be.gerard.core.interface_v1.AuthenticationService;
import be.gerard.core.interface_v1.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;

@Scope("session")
@Named("loginBean")
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CommonAuthenticationProvider commonAuthenticationProvider;

    @Value("${be.gerard.core.application.name}")
    private String applicationName;

    private String username;

    private String password;

    private UserSession session;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSession getSession() {
        return session;
    }

    //

    public void login() {
        login(username, password);
    }

    public void login(final String username, final String password) {
        try {
            CommonAuthenticationToken authentication = commonAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                session = authentication.getSession();
            }
        } catch (ServiceException e) {
            e.getErrors().entrySet().stream().forEach((_error) -> {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, _error.getKey().getKey(), Arrays.toString(_error.getValue())));
            });
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void logout() {
        authenticationService.logout(session.getToken());
        SecurityContextHolder.getContext().setAuthentication(null);

        session = null;
        username = null;
        password = null;
    }

}
