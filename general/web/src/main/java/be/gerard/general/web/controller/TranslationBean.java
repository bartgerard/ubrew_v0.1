package be.gerard.general.web.controller;

import be.gerard.core.interface_v1.exception_v1.ServiceException;
import be.gerard.general.importer.TranslationImporter;
import be.gerard.general.interface_v1.TranslationService;
import be.gerard.general.interface_v1.model.Translation;
import be.gerard.general.util.MessageUtil;
import be.gerard.core.interface_v1.web.GeneralListBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

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
    private MessageUtil i18n;

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
                    getTranslationService().remove(getSelected(), loginBean.getSession());
                } else {
                    getTranslationService().saveOrUpdate(getSelected(), loginBean.getSession());
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
            getTranslationService().saveOrUpdate(TranslationImporter.importFromExcel(event.getFile().getInputstream(), "TODO"), loginBean.getSession());
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
