package be.gerard.core.web.controller;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.common.web.GeneralListBean;
import be.gerard.core.interface_v1.UserService;
import be.gerard.core.interface_v1.model.User;
import be.gerard.common.web.util.JsfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Scope("session")
@Named
//@Stateless
public class UserBean extends GeneralListBean<User> implements Serializable {

    @Autowired
    private UserService userService;

    @Value("${be.gerard.general.application.name}")
    private String applicationName;

    private String password;

    public UserService getUserService() {
        return userService;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User prepareCreate() {
        setSelected(new User());
        return getSelected();
    }

    @Override
    protected List<User> reloadItems() {
        return getUserService().findAll();
    }

    @Override
    public List<User> getItems() {
        return super.getItems();
    }

    @Override
    public void persist(final PersistAction persistAction, final String successMessage) {
        if (getSelected() != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    getUserService().save(getSelected(), password);
                } else {
                    //getUserService().remove(getSelected());
                }
                JsfUtil.addSuccessMessage(successMessage);
            }
//            catch (UserServiceException ex) {
//                String msg = "";
//                Throwable cause = ex.getCause();
//                if (cause != null) {
//                    msg = cause.getLocalizedMessage();
//                }
//                if (msg.length() > 0) {
//                    JsfUtil.addErrorMessage(msg);
//                } else {
//                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//                }
//            }
            catch (ServiceException e) {
                handleException(e);
            }
        }
    }

    @Override
    public List<User> getItemsAvailableSelectMany() {
        return getUserService().findAll();
    }

    @Override
    public List<User> getItemsAvailableSelectOne() {
        return getUserService().findAll();
    }

    public User getUser(final String username) {
        return getUserService().findByUsername(username);
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserBean controller = (UserBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userBean");
            return controller.getUser(value);
        }

//        Long getKey(String value) {
//            return Long.valueOf(value);
//        }
//
//        String getStringKey(Long value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof User) {
                User o = (User) object;
                return o.getUsername();
                //return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }

}
