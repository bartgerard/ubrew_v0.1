package be.gerard.general.web.controller;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.general.interface_v1.UserService;
import be.gerard.general.interface_v1.model.UserDetail;
import be.gerard.common.web.GeneralListBean;
import be.gerard.general.web.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Scope("session")
@Named
//@Stateless
public class UserBean extends GeneralListBean<UserDetail> implements Serializable {

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

    public UserDetail prepareCreate() {
        setSelected(new UserDetail());
        return getSelected();
    }

    @Override
    protected List<UserDetail> reloadItems() {
        return getUserService().findAll(applicationName);
    }

    @Override
    public List<UserDetail> getItems() {
        return super.getItems();
    }

    @Override
    public void persist(final PersistAction persistAction, final String successMessage) {
        if (getSelected() != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    getUserService().saveOrUpdate(getSelected(), password, applicationName);
                } else {
                    getUserService().remove(getSelected());
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
    public List<UserDetail> getItemsAvailableSelectMany() {
        return getUserService().findAll(applicationName);
    }

    @Override
    public List<UserDetail> getItemsAvailableSelectOne() {
        return getUserService().findAll(applicationName);
    }

    public UserDetail getUser(final Long id) {
        return getUserService().find(id, applicationName);
    }

    @FacesConverter(forClass = UserDetail.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserBean controller = (UserBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userBean");
            return controller.getUser(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UserDetail) {
                UserDetail o = (UserDetail) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + UserDetail.class.getName());
            }
        }

    }

}
