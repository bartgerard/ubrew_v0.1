package be.gerard.translation.service;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.general.interface_v1.UserService;
import be.gerard.general.interface_v1.model.Address;
import be.gerard.general.interface_v1.model.User;
import be.gerard.general.interface_v1.model.UserSession;
import be.gerard.translation.interface_v1.TranslationService;
import be.gerard.translation.interface_v1.enums.Language;
import be.gerard.translation.interface_v1.model.Translation;
import be.gerard.translation.service.dao.TranslationDAO;
import be.gerard.translation.service.model.TranslationRecord;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author bartgerard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:be.gerard.translation.service.test.xml")
public class TranslationTest {
    
    @Autowired
    private TranslationDAO translationDAO;
    
    @Autowired
    private TranslationService translationService;
    
    @Autowired
    private UserService userService;
    
    @Value(value = "${be.gerard.translation.application.default}")
    private String applicationDefault;
    
    private final static String NL = Language.NL.getCode(), FR = Language.FR.getCode();
    
    @Test
    public void ping() {
        Assert.assertNotNull(translationService.ping());
    }
    
    @Test
    public void saveOrUpdate() {
        TranslationRecord translationRecord_NL = new TranslationRecord(applicationDefault, NL, "KEY", "sleutel", "TEST");
        translationDAO.saveOrUpdate(translationRecord_NL);
        Assert.assertNotNull(translationRecord_NL.getId());
        Assert.assertEquals(applicationDefault, translationRecord_NL.getApplication());
        Assert.assertEquals(NL, translationRecord_NL.getLanguage());
        Assert.assertEquals("KEY", translationRecord_NL.getKey());
        Assert.assertEquals("sleutel", translationRecord_NL.getValue());
        Assert.assertEquals("TEST", translationRecord_NL.getChangedBy());
        
        TranslationRecord translationRecord2 = translationDAO.get(applicationDefault, NL, "KEY");
        Assert.assertEquals(translationRecord_NL, translationRecord2);
        
        TranslationRecord translationRecord3 = translationDAO.get(applicationDefault, FR, "KEY");
        Assert.assertNull(translationRecord3);
        
        TranslationRecord translationRecord_FR = new TranslationRecord(applicationDefault, FR, "KEY", "clé", "TEST");
        translationDAO.saveOrUpdate(translationRecord_FR);
        Assert.assertNotNull(translationRecord_FR.getId());
        Assert.assertEquals(applicationDefault, translationRecord_FR.getApplication());
        Assert.assertEquals(FR, translationRecord_FR.getLanguage());
        Assert.assertEquals("KEY", translationRecord_FR.getKey());
        Assert.assertEquals("clé", translationRecord_FR.getValue());
        Assert.assertEquals("TEST", translationRecord_FR.getChangedBy());
        
        translationRecord2 = translationDAO.get(applicationDefault, NL, "KEY");
        Assert.assertEquals(translationRecord_NL, translationRecord2);
        
        TranslationRecord translationRecord5 = translationDAO.get(applicationDefault, FR, "KEY");
        Assert.assertEquals(translationRecord_FR, translationRecord5);
    }
    
    @Test
    public void create() throws ServiceException {
        EasyMock.expect(userService.validate(EasyMock.anyObject(UserSession.class))).andReturn(Boolean.TRUE);
        EasyMock.replay(userService);
        
        Translation translation = translationService.saveOrUpdate(new Translation(applicationDefault, NL, "VALUE", "waarde"), new UserSession(UUID.randomUUID().toString(), new User("test@ubrew.be", "iBrew", "iBrew", new Date(), new Address("Street", null, "City", 2100, "Antwerp", "Country")), LocalDateTime.now()));
        Assert.assertNotNull(translation.getId());
    }
    
}
