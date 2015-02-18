package be.gerard.ubrew.core.web.controller;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.general.interface_v1.UserAuthenticationService;
import be.gerard.general.interface_v1.session.UserSession;
import java.io.Serializable;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Scope("session")
@Named("loginBean")
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PageBean pageBean;

    @Autowired
    private UserAuthenticationService authenticationService;

    @Value("${be.gerard.general.application.name}")
    private String applicationName;
    
    private String username;

    private String password;
    
    private UserSession session;

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

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
            session = authenticationService.login(username, password);
            pageBean.setPage(PageBean.PAGE.CLICK_AND_BREW);
        } catch (ServiceException e) {
            e.getErrors().entrySet().stream().forEach((_error) -> {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, _error.getKey().getKey(), Arrays.toString(_error.getValue())));
            });
            FacesContext.getCurrentInstance().validationFailed();
        }
    }

    public void logout() {
        authenticationService.logout(session.getToken());
        
        session = null;
        username = null;
        password = null;

        pageBean.setPage(PageBean.PAGE.HOME);
    }

}
