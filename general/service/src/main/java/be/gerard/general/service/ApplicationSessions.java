package be.gerard.general.service;

import be.gerard.general.service.session.ApplicationSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 * ApplicationSessions
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Component
public class ApplicationSessions {

    private final Map<UUID, ApplicationSession> sessions = new HashMap<>();

    Map<UUID, ApplicationSession> getSessions() {
        return sessions;
    }

}
