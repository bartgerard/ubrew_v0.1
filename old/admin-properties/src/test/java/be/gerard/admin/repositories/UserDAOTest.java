/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.gerard.admin.repositories;

import be.gerard.admin.entities.UserVO;
import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author bartgerard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/app-test-config.xml")
public class UserDAOTest {
    
    @Autowired
    private UserDAO userDAO;
    
    @Test
    public void testCreateUser() {
        userDAO.saveOrUpdate(new UserVO("admin", "admin", "test@gmail.com", Calendar.getInstance()));
    }
    
}
