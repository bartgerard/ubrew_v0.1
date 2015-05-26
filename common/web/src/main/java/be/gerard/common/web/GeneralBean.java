package be.gerard.common.web;

import be.gerard.common.exception_v1.ServiceException;
import java.io.Serializable;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import be.gerard.common.web.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author bartgerard
 * @param <T>
 */
public abstract class GeneralBean<T extends Serializable> implements Serializable {

    @Autowired
    private MessageUtils i18n;

    public enum PersistAction {

        CREATE,
        DELETE,
        UPDATE
    }

    private T selected;

    public MessageUtils getI18n() {
        return i18n;
    }

    public T getSelected() {
        return selected;
    }

    public void setSelected(T selected) {
        this.selected = selected;
    }

    protected abstract void persist(final PersistAction persistAction, final String successMessage);

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public void handleException(ServiceException e) {
        e.getErrors().entrySet().stream().forEach((_error) -> {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, i18n.get(_error.getKey().name()), Arrays.toString(_error.getValue())));
        });
        FacesContext.getCurrentInstance().validationFailed();
    }

}
