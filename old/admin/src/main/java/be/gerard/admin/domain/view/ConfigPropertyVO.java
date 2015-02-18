/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gerard.admin.domain.view;

/**
 * Language Property Value Object
 * 
 * @author bartgerard
 */
public class ConfigPropertyVO extends GeneralPropertyVO {

    public ConfigPropertyVO() {
    }

    public ConfigPropertyVO(String application, String key, String value) {
        super(application, key, value);
    }

    @Override
    public String toString() {
        return "ConfigPropertyVO1{" + '}';
    }

}
