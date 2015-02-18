package be.gerard.general.service.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.util.Assert;

/**
 * ApplicationRecord
 *
 * @author bartgerard
 * @version 0.0.1
 */
@Entity
@Table(name = "general_application", uniqueConstraints = @UniqueConstraint(name = "uk_application_key", columnNames = "app_key"))
public class ApplicationRecord implements Serializable {

    @Id
    @Column(name = "app_key", nullable = false)
    private String key;

    @Column(name = "app_pass", nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "rel_application_ip",
            joinColumns = @JoinColumn(name = "application_id"),
            foreignKey = @ForeignKey(name = "fk_ip_application"),
            uniqueConstraints = @UniqueConstraint(name = "uk_application_ip", columnNames = {"application_id", "ip"})
    )
    @Column(name = "ip")
    private final Set<String> allowableIps = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "rel_application_mac",
            joinColumns = @JoinColumn(name = "application_id"),
            foreignKey = @ForeignKey(name = "fk_mac_application"),
            uniqueConstraints = @UniqueConstraint(name = "uk_application_mac", columnNames = {"application_id", "mac"})
    )
    @Column(name = "mac")
    private final Set<String> allowableMacs = new HashSet<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getAllowableIps() {
        return Collections.unmodifiableSet(allowableIps);
    }

    public void addIp(String ip) {
        Assert.hasText(ip, String.format("ip is invalid [%s]", ip));
        Assert.isTrue(allowableIps.add(ip), String.format("ip [%s] was not added", ip));
    }

    public void removeIp(String ip) {
        Assert.hasText(ip, String.format("ip is invalid [%s]", ip));
        Assert.isTrue(allowableIps.remove(ip), String.format("ip [%s] was not removed", ip));
    }

    public Set<String> getAllowableMacs() {
        return Collections.unmodifiableSet(allowableMacs);
    }

    public void addMac(String mac) {
        Assert.hasText(mac, String.format("mac is invalid [%s]", mac));
        Assert.isTrue(allowableIps.add(mac), String.format("mac [%s] was not added", mac));
    }

    public void removeMac(String mac) {
        Assert.hasText(mac, String.format("mac is invalid [%s]", mac));
        Assert.isTrue(allowableIps.remove(mac), String.format("mac [%s] was not removed", mac));
    }

}
