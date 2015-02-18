package be.gerard.core.service.dao;

import be.gerard.core.service.BaseTest;
import be.gerard.core.user.service.dao.UserDao;
import be.gerard.core.user.service.model.UserRecord;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

/**
 * UserDaoTest
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class UserDaoTest extends BaseTest {
    
    @Autowired
    private UserDao userDao;

    @Test
    public void test() {
        UserRecord userRecord = new UserRecord();
        userRecord.setUsername(USERNAME);
        userRecord.setEncryptedPassword(PASSWORD);
        userRecord.setFirstname("Firstname");
        userRecord.setLastname("Lastname");
        userRecord.setBirthDate(LocalDate.now());
        
        userDao.saveAndFlush(userRecord);
        
        UserRecord userRecordAfterFindOne = userDao.findOne(USERNAME);
        Assert.assertNotNull("userDao.findOne did return null", userRecordAfterFindOne);
        
        UserRecord userRecordAfterFindByUsername = userDao.findByUsername(USERNAME);
        Assert.assertNotNull("userDao.findByUsername did return null", userRecordAfterFindByUsername);
        
        userDao.delete(userRecord);
        Assert.assertNull("userDao did not delete record", userDao.findOne(USERNAME));
        
        Assert.assertNull("userDao.findByUsername did not return null", userDao.findByUsername(USERNAME + "_non_existing"));
    }

}
