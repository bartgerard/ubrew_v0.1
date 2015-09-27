package be.gerard.core.service.script;

import be.gerard.core.interface_v1.ApplicationService;
import be.gerard.core.interface_v1.enums.PropertyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Collections;

/**
 * ApplicationScript
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class ApplicationScript extends CommonScript {

    public static final String APP_KEY = "key_123";
    public static final String REFERENCE = "instance_1";
    public static final String IP = "127.0.0.1";
    public static final String MAC = "AB:CD:EF:GH";
    public static final String PROP1_KEY = "property1";
    public static final String PROP1_GROUP = "group1";
    public static final String PROP1_VALUE = "value1";

    @Autowired
    private ApplicationService applicationService;

    @Test
    @Rollback(false)
    public void initApplications() {
        builderContext.buildApplication("core.web")
                .propertyGroup("core")
                .property("be.gerard.core.service.url", PropertyType.URL, "http://localhost:8080/core-service")
                .and()
                .build().save();

        builderContext.buildApplication("ubrew.web")
                .propertyGroup("core")
                .and()
                .build().save();

        applicationService.instantiate(
                "core.web",
                "core.web.1",
                "password",
                Collections.singletonList("127.0.0.1"),
                Collections.emptyList()
        );

        applicationService.instantiate(
                "ubrew.web",
                "ubrew.web.1",
                "password",
                Collections.singletonList("127.0.0.1"),
                Collections.emptyList()
        );
    }

}
