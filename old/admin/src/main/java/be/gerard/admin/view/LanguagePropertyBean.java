/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gerard.admin.view;

import be.gerard.admin.config.Constants;
import be.gerard.admin.domain.view.LanguagePropertyVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author bartgerard
 */
@SessionScoped
@ManagedBean
public class LanguagePropertyBean implements Serializable {

    @ManagedProperty("#{configPropertyBean}")
    private ConfigPropertyBean configPropertyBean;

    public ConfigPropertyBean getConfigPropertyBean() {
        return configPropertyBean;
    }

    public void setConfigPropertyBean(ConfigPropertyBean configPropertyBean) {
        this.configPropertyBean = configPropertyBean;
    }

    public LanguagePropertyBean() {
    }

    private final List<LanguagePropertyVO> languageProperties = new ArrayList<>();

    {
        languageProperties.add(new LanguagePropertyVO("admin", "translations", "nl", "Vertalingen"));
        languageProperties.add(new LanguagePropertyVO("admin", "translations", "en", "Translations"));
        languageProperties.add(new LanguagePropertyVO("admin", "application", "nl", "Toepassing"));
        languageProperties.add(new LanguagePropertyVO("admin", "application", "en", "Application"));
        languageProperties.add(new LanguagePropertyVO("admin", "key", "nl", "Key"));
        languageProperties.add(new LanguagePropertyVO("admin", "key", "en", "Key"));
        languageProperties.add(new LanguagePropertyVO("admin", "language", "nl", "Taal"));
        languageProperties.add(new LanguagePropertyVO("admin", "language", "en", "Language"));
        languageProperties.add(new LanguagePropertyVO("admin", "value", "nl", "Waarde"));
        languageProperties.add(new LanguagePropertyVO("admin", "value", "en", "Value"));
        languageProperties.add(new LanguagePropertyVO("admin", "configuration", "nl", "Configuratie"));
        languageProperties.add(new LanguagePropertyVO("admin", "configuration", "en", "Configuration"));
    }

    private final static String APPLICATION = "admin";

    public List<LanguagePropertyVO> getLanguageProperties() {
        return Collections.unmodifiableList(languageProperties);
    }

    public String get(String application, String key) {
        for (LanguagePropertyVO languagePropertyVO : languageProperties) {
            if (languagePropertyVO.getApplication().equals(APPLICATION) && languagePropertyVO.getKey().equals(key) && languagePropertyVO.getLanguage().equals(configPropertyBean.getByKey(Constants.DEFAULT_LANGUAGE.getValue()))) {
                return languagePropertyVO.getValue();
            }
        }

        return null;
    }

    public void onCellEdit() {

    }

}
