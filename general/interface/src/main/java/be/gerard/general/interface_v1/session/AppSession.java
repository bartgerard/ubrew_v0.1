package be.gerard.general.interface_v1.session;

import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * AppSession
 *
 * @author bartgerard
 * @version 0.0.1
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appSession")
public class AppSession extends BaseSession<UUID> {

    public AppSession() {
    }

    public AppSession(UUID token) {
        super(token);
    }

}
