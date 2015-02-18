/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.gerard.ubrew.database.dao;

import be.gerard.ubrew.database.dao.hibernate.UserDAOHib;
import be.gerard.ubrew.database.entities.Address;
import be.gerard.ubrew.database.entities.User;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bartgerard
 */
public class UserDAOTest {
    
    private final UserDAO userDao = new UserDAOHib();

    //private final Users users = new Users();
    public UserDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        for (User user : userDao.list()) {
            userDao.delete(user);
        }
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void createAndGet() {
        User u1 = userDao.saveOrUpdate(new User("a", "b", "c", "d"));
        u1.addAddress(new Address());
        userDao.saveOrUpdate(u1);
        
        userDao.saveOrUpdate(new User("e", "f", "g", "h"));
        
        Assert.assertEquals(2, userDao.list().size());
        
        User u2 = userDao.get(u1.getId());
        
        Assert.assertTrue(u1.equals(u2));
    }
    
    @Test
    public void login() {
        User u1 = userDao.saveOrUpdate(new User("a", "b", "c", "d"));
        
        User u2 = userDao.login("a", "b");
        
        Assert.assertTrue(u1.equals(u2));
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void createDoublePrimaryKey() {
        userDao.saveOrUpdate(new User("a", "b", "c", "d"));
        userDao.saveOrUpdate(new User("a", "f", "g", "h"));
    }

}
