/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gerard.admin.domain.view;

import java.io.Serializable;
import java.util.Objects;

/**
 * General Property Value Object
 * 
 * @author bartgerard
 */
public abstract class GeneralPropertyVO implements Serializable {

    public GeneralPropertyVO() {
    }

    public GeneralPropertyVO(String application, String key, String value) {
        this.application = application;
        this.key = key;
        this.value = value;
    }

    private String application;

    private String key;

    private String value;

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.application);
        hash = 29 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GeneralPropertyVO other = (GeneralPropertyVO) obj;
        if (!Objects.equals(this.application, other.application)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneralProperty{" + "application=" + application + ", key=" + key + ", value=" + value + '}';
    }

}
