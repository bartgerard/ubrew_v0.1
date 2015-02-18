/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gerard.admin.domain.view;

import java.util.Objects;

/**
 * Language Property Value Object
 * 
 * @author bartgerard
 */
public class LanguagePropertyVO extends GeneralPropertyVO {

    public LanguagePropertyVO() {
    }

    public LanguagePropertyVO(String application, String key, String language, String value) {
        super(application, key, value);
        this.language = language;
    }

    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.language);
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
        final LanguagePropertyVO other = (LanguagePropertyVO) obj;
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LanguageProperty{" + "language=" + language + '}';
    }

}
