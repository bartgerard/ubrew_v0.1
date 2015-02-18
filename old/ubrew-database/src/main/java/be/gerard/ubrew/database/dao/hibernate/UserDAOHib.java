/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gerard.ubrew.database.dao.hibernate;

import be.gerard.ubrew.database.entities.User;
import be.gerard.ubrew.database.dao.UserDAO;
import javax.ejb.Stateless;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * UserDAO Hibernate Implementation
 *
 * @author bartgerard
 * @version v0.0.1
 *
 */
@Stateless
public class UserDAOHib extends DAOHib<User, Long> implements UserDAO {
    
    private static final Logger logger = LogManager.getLogger(UserDAO.class.getName());
    
    private final PasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

    @Override
    public User saveOrUpdate(User user) {
        user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
        return super.saveOrUpdate(user);
    }

    @Override
    public User login(String username, String password) {
        logger.entry(username);
        
        Session session = openSession();
        User user = (User) session.createCriteria(User.class).add(Restrictions.ilike("username", username)).uniqueResult();
        session.close();
        
        if (user != null && passwordEncryptor.checkPassword(password, user.getPassword())) {
            return user;
        } else {
            logger.error(user);
            return null;
        }
    }

}
