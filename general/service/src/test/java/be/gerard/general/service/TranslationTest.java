package be.gerard.general.service;

import be.gerard.common.authentication.util.UserSessionUtils;
import be.gerard.common.exception_v1.ServiceException;
import be.gerard.general.interface_v1.TranslationService;
import be.gerard.general.interface_v1.UserAuthenticationService;
import be.gerard.general.interface_v1.UserService;
import be.gerard.general.interface_v1.enums.Language;
import be.gerard.general.interface_v1.model.Translation;
import be.gerard.general.interface_v1.model.UserDetail;
import be.gerard.general.interface_v1.session.UserSession;
import be.gerard.general.service.dao.TranslationDAO;
import be.gerard.general.service.model.TranslationRecord;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bartgerard
 */
@ActiveProfiles("jpa")
public class TranslationTest extends BaseTest {
    
    @Autowired
    private TranslationDAO translationDAO;
    
    @Autowired
    private TranslationService translationService;
    
    @Autowired
    private UserAuthenticationService authenticationService;
    
    @Autowired
    private UserService userService;
    
    @Value(value = "${be.gerard.general.translation.application.default}")
    private String applicationDefault;
    
    private final static String NL = Language.NL.getCode(), FR = Language.FR.getCode();
    
    @Before
    public void before() {
        UserSession userSession = new UserSession(new UserDetail("test@ubrew.be", "Test", "Ubrew", new Date()));
        UserSessionUtils.setUserSession(userSession);
    }
    
    @Test
    public void ping() {
        Assert.assertNotNull(translationService.ping());
    }
    
    @Test
    @Transactional
    public void saveOrUpdate() {
        TranslationRecord translationRecord_NL = new TranslationRecord(applicationDefault, NL, "KEY", "sleutel");
        translationDAO.saveOrUpdate(translationRecord_NL);
        Assert.assertNotNull(translationRecord_NL.getId());
        Assert.assertEquals(applicationDefault, translationRecord_NL.getApplication());
        Assert.assertEquals(NL, translationRecord_NL.getLanguage());
        Assert.assertEquals("KEY", translationRecord_NL.getKey());
        Assert.assertEquals("sleutel", translationRecord_NL.getValue());
        
        TranslationRecord translationRecord2 = translationDAO.find(applicationDefault, NL, "KEY");
        Assert.assertEquals(translationRecord_NL, translationRecord2);
        
        TranslationRecord translationRecord3 = translationDAO.find(applicationDefault, FR, "KEY");
        Assert.assertNull(translationRecord3);
        
        TranslationRecord translationRecord_FR = new TranslationRecord(applicationDefault, FR, "KEY", "clé");
        translationDAO.saveOrUpdate(translationRecord_FR);
        Assert.assertNotNull(translationRecord_FR.getId());
        Assert.assertEquals(applicationDefault, translationRecord_FR.getApplication());
        Assert.assertEquals(FR, translationRecord_FR.getLanguage());
        Assert.assertEquals("KEY", translationRecord_FR.getKey());
        Assert.assertEquals("clé", translationRecord_FR.getValue());
        
        translationRecord2 = translationDAO.find(applicationDefault, NL, "KEY");
        Assert.assertEquals(translationRecord_NL, translationRecord2);
        
        TranslationRecord translationRecord5 = translationDAO.find(applicationDefault, FR, "KEY");
        Assert.assertEquals(translationRecord_FR, translationRecord5);
    }
    
    @Test
    @Transactional
    public void create() throws ServiceException {
        //EasyMock.expect(userService.validate(EasyMock.anyObject(UserSession.class))).andReturn(Boolean.TRUE);
        //EasyMock.replay(userService);
        
        UserDetail user = new UserDetail("a@a.a.a", "A", "A", new Date());
        userService.saveOrUpdate(user, "a12345", applicationDefault);
        UserSession session = authenticationService.login("a@a.a.a", "a12345");
        Translation translation = translationService.saveOrUpdate(new Translation(applicationDefault, NL, "VALUE", "waarde"), session);
        Assert.assertNotNull(translation.getId());
    }
    
}
