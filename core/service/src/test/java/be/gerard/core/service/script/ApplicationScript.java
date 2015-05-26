package be.gerard.core.service.script;

import be.gerard.core.interface_v1.ApplicationService;
import be.gerard.core.service.BaseTest;
import be.gerard.core.service.builder.BuilderContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * ApplicationScript
 *
 * @author bartgerard
 * @version v0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:be.gerard.core.service.xml"})
@Transactional
public class ApplicationScript extends BaseTest {

    @Autowired
    private BuilderContext builderContext;

    @Autowired
    private ApplicationService applicationService;

    @Test
    @Rollback(false)
    public void initApplications() {
        builderContext.load();
        builderContext.buildApplication("core.web")
                .save();
    }

}
