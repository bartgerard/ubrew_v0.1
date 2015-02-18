package be.gerard.general.service.session;

import be.gerard.general.interface_v1.session.BaseSession;
import be.gerard.general.interface_v1.session.UserSession;
import be.gerard.general.service.model.ApplicationDetailRecord;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.xml.bind.annotation.XmlElement;

/**
 * ApplicationSession
 *
 * @author bartgerard
 * @version 0.0.1
 */
public class ApplicationSession extends BaseSession<UUID> {

    private final Map<UUID, UserSession> sessions = new HashMap<>();

    @XmlElement(required = true)
    private final ApplicationDetailRecord application;

    public ApplicationSession(ApplicationDetailRecord application) {
        super(UUID.randomUUID());
        this.application = application;
    }

    public ApplicationDetailRecord getApplication() {
        return application;
    }

    public Map<UUID, UserSession> getSessions() {
        return sessions;
    }

    public UserSession getSession(UUID token) {
        return sessions.get(token);
    }

    public UserSession register(UserSession session) {
        return sessions.put(session.getToken(), session);
    }

    public UserSession unregister(UserSession session) {
        return sessions.remove(session.getToken());
    }

}
