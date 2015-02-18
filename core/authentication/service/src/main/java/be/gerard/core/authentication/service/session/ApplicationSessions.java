package be.gerard.core.authentication.service.session;

import be.gerard.core.authentication.interface_v1.session.AppSession;
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

    private final Map<UUID, AppSession> sessions = new HashMap<>();

    Map<UUID, AppSession> getSessions() {
        return sessions;
    }

}
