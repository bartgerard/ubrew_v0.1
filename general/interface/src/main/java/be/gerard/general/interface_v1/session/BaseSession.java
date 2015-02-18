package be.gerard.general.interface_v1.session;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;

/**
 * BaseSession
 *
 * @author bartgerard
 * @version 0.0.1
 *
 * @param <T> Token
 */
public abstract class BaseSession<T extends Serializable> implements Serializable {

    @XmlElement(required = true)
    private T token;

    @XmlElement(required = true)
    private LocalDateTime lastActivity = LocalDateTime.now();

    public BaseSession() {
    }

    public BaseSession(T token) {
        this.token = token;
    }

    public T getToken() {
        return token;
    }

    public void getToken(T token) {
        this.token = token;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.token);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseSession other = (BaseSession) obj;
        return Objects.equals(this.token, other.token);
    }

    @Override
    public String toString() {
        return "CustomSession{" + "token=" + token + ", lastActivity=" + lastActivity + '}';
    }

}
