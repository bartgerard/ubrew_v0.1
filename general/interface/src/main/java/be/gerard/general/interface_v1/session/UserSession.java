package be.gerard.general.interface_v1.session;

import be.gerard.general.interface_v1.model.UserDetail;
import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * UserSession
 * 
 * @author bartgerard
 * @version 0.0.1
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userSession")
public class UserSession extends BaseSession<UUID> {

    public UserSession() {
        super(UUID.randomUUID());
    }

    public UserSession(UserDetail user) {
        this();
        this.user = user;
    }

    @XmlElement(required = true)
    private UserDetail user;

    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }

}
