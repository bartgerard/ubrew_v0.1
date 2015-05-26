package be.gerard.core.web.controller;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.common.web.GeneralListBean;
import be.gerard.core.importer.TranslationImporter;
import be.gerard.core.interface_v1.TranslationService;
import be.gerard.core.interface_v1.model.Translation;
import be.gerard.common.web.util.MessageUtils;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author bartgerard
 */
@Scope("session")
@Named
public class TranslationBean extends GeneralListBean<Translation> implements Serializable {

    @Autowired
    private TranslationService translationService;

    @Autowired
    private LoginBean loginBean;
    
    @Autowired
    private MessageUtils i18n;

    public TranslationService getTranslationService() {
        return translationService;
    }

    public Translation prepareCreate() {
        setSelected(new Translation());
        return getSelected();
    }

    @Override
    protected List<Translation> reloadItems() {
        return getTranslationService().findAll();
    }
    
    @Override
    public List<Translation> getItems() {
        return super.getItems();
    }

    @Override
    protected void persist(PersistAction persistAction, String successMessage) {
        if (getSelected() != null) {
            try {
                if (persistAction == PersistAction.DELETE) {
                    getTranslationService().remove(getSelected());
                } else {
                    getTranslationService().save(getSelected());
                }
            } catch (ServiceException e) {
                handleException(e);
            }
        }
    }

    @Override
    public List<Translation> getItemsAvailableSelectMany() {
        return getTranslationService().findAll();
    }

    @Override
    public List<Translation> getItemsAvailableSelectOne() {
        return getTranslationService().findAll();
    }

    public void handleFileUpload(final FileUploadEvent event) {
        try {
            getTranslationService().save(TranslationImporter.importFromExcel(event.getFile().getInputstream(), "TODO"));
            clearItems();
            FacesContext.getCurrentInstance().addMessage(":TranslationUploadForm:messages", new FacesMessage(FacesMessage.SEVERITY_INFO, i18n.get("SUC6"), ""));
        } catch (ServiceException e) {
            handleException(e);
            //handleException(e, ":languageUploadForm:messages");
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(":TranslationUploadForm:messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, i18n.get("LANGUAGEPROPERTIESPAGE_POPUP_ERROR_REQUIRED_APPLICATION"), ""));
        }
    }

}
