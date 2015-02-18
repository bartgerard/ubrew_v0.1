/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.gerard.admin.view;

import be.gerard.admin.config.Constants;
import be.gerard.admin.domain.view.ConfigPropertyVO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author bartgerard
 */
@SessionScoped
@ManagedBean(name = "configPropertyBean")
public class ConfigPropertyBean implements Serializable {

    public ConfigPropertyBean() {
    }
    
    private final List<ConfigPropertyVO> configProperties = new ArrayList<>();
    
    {
        configProperties.add(new ConfigPropertyVO("admin", Constants.DEFAULT_LANGUAGE.getValue(), "nl"));
        configProperties.add(new ConfigPropertyVO("admin", "test", "true"));
    }
    
    private final static String APPLICATION = "admin";

    public List<ConfigPropertyVO> getConfigProperties() {
        return Collections.unmodifiableList(configProperties);
    }
    
    public String getByKey(String key) {
        for (ConfigPropertyVO configProperty : configProperties) {
            if (configProperty.getApplication().equals(APPLICATION) && configProperty.getKey().equals(key)) {
                return configProperty.getValue();
            }
        }
        
        return null;
    }
    
    public void onCellEdit() {
        
    }
    
}
