package be.gerard.core.interface_v1.session;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * AppSession
 *
 * @author bartgerard
 * @version 0.0.1
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appSession")
public class AppSession extends BaseSession<UUID> {

    private final Map<String, String> properties = new HashMap<>();

    public AppSession() {
        super(UUID.randomUUID());
    }

    public AppSession(UUID token) {
        super(token);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

}