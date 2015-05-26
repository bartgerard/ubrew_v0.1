package be.gerard.common.db.model;

import be.gerard.core.interface_v1.util.UserSessionUtils;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.util.Assert;

/**
 * BaseRecord
 *
 * @author bartgerard
 * @version 0.0.1
 */
@MappedSuperclass
public abstract class BaseRecord extends SimpleBaseRecord {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_ts", nullable = false, updatable = false)
    private Date createTimestamp;

    @Column(name = "create_user", nullable = false, updatable = false)
    private String createUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_ts", nullable = false)
    private Date updateTimestamp;

    @Column(name = "update_user", nullable = false)
    private String updateUser;

    public BaseRecord() {
    }

    @Deprecated
    public BaseRecord(Long id) {
        super(id);
    }

    @PrePersist
    public void onCreate() {
        onUpdate();
        this.createTimestamp = this.updateTimestamp;
        this.createUser = this.updateUser;
    }

    @PreUpdate
    public void onUpdate() {
        Assert.notNull(UserSessionUtils.getUserSession(), "userSession is invalid [null]");
        Assert.notNull(UserSessionUtils.getUserSession().getUser(), "userSession.user is invalid [null]");
        
        this.updateTimestamp = new Date();
        this.updateUser = UserSessionUtils.getUserSession().getUser().getUsername();
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public String getUpdateUser() {
        return updateUser;
    }

}
