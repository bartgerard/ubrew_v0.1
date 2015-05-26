package be.gerard.core.interface_v1.session;

import be.gerard.core.interface_v1.model.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

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

    public UserSession(User user) {
        this();
        this.user = user;
    }

    @XmlElement(required = true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
