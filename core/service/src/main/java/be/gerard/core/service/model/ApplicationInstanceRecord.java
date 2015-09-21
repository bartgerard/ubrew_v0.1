package be.gerard.core.service.model;

import be.gerard.common.db.model.BaseRecord;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * ApplicationInstanceRecord
 *
 * @author bartgerard
 * @version 0.0.1
 * @since 2015-01-10 19:03
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_application_instance", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "core_application_instance", uniqueConstraints = @UniqueConstraint(name = "uk_appInstance_appIdAndAppRef", columnNames = {"application_id", "app_ref"}))
public class ApplicationInstanceRecord extends BaseRecord {

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "application_id",
            foreignKey = @ForeignKey(name = "fk_appins2app_application")
    )
    private ApplicationRecord application;

    @Column(name = "app_ref", nullable = false)
    private String reference;

    @Column(name = "app_pass", nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "rel_application2ip",
            joinColumns = @JoinColumn(name = "application_id"),
            uniqueConstraints = @UniqueConstraint(name = "uk_application_ip", columnNames = {"application_id", "ip"}),
            foreignKey = @ForeignKey(name = "fk_app2ip_application")
    )
    @Column(name = "ip")
    private final Set<String> allowedIps = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "rel_application2mac",
            joinColumns = @JoinColumn(name = "application_id"),
            uniqueConstraints = @UniqueConstraint(name = "uk_application_mac", columnNames = {"application_id", "mac"}),
            foreignKey = @ForeignKey(name = "fk_app2mac_application")
    )
    @Column(name = "mac")
    private final Set<String> allowedMacs = new HashSet<>();

    public ApplicationInstanceRecord(ApplicationRecord application, String reference, String password) {
        this.application = application;
        this.reference = reference;
        this.password = password;
    }

    private ApplicationInstanceRecord() {
    }

    public ApplicationRecord getApplication() {
        return application;
    }

    public String getReference() {
        return reference;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getAllowedIps() {
        return Collections.unmodifiableSet(allowedIps);
    }

    public void addIp(String ip) {
        Assert.hasText(ip, String.format("ip is invalid [%s]", ip));
        Assert.isTrue(allowedIps.add(ip), String.format("ip [%s] was not added", ip));
    }

    public void removeIp(String ip) {
        Assert.hasText(ip, String.format("ip is invalid [%s]", ip));
        Assert.isTrue(allowedIps.remove(ip), String.format("ip [%s] was not removed", ip));
    }

    public boolean isIpAllowed(String ip) {
        return this.allowedIps.contains(ip);
    }

    public void clearAllowedIps() {
        allowedIps.clear();
    }

    public Set<String> getAllowedMacs() {
        return Collections.unmodifiableSet(allowedMacs);
    }

    public void addMac(String mac) {
        Assert.hasText(mac, String.format("mac is invalid [%s]", mac));
        Assert.isTrue(allowedMacs.add(mac), String.format("mac [%s] was not added", mac));
    }

    public void removeMac(String mac) {
        Assert.hasText(mac, String.format("mac is invalid [%s]", mac));
        Assert.isTrue(allowedMacs.remove(mac), String.format("mac [%s] was not removed", mac));
    }

    public boolean isMacAllowed(String mac) {
        return this.allowedMacs.contains(mac);
    }

    public void clearAllowedMacs() {
        allowedMacs.clear();
    }

}
