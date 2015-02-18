/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.gerard.admin.config;

/**
 *
 * @author bartgerard
 */
public enum Constants {

    /**
     *
     */
    DEFAULT_LANGUAGE("default_language");

    private Constants(String value) {
        this.value = value;
    }
    
    private final String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
    
}
