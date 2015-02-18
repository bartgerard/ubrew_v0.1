/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.gerard.ubrew.database.dao;

import be.gerard.ubrew.database.entities.User;

/**
 *
 * @author bartgerard
 */
public interface UserDAO extends DAO<User, Long> {

    @Override
    public User saveOrUpdate(User e);
    
    public User login(String username, String password);
    
}
