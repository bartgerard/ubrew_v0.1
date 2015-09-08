package be.gerard.ubrew.service.model;

import be.gerard.common.db.model.BaseRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.util.Assert;

/**
 * OrderRecord
 *
 * @author Bart Gerard
 */
@Entity
@SequenceGenerator(name = BaseRecord.SEQUENCE_GENERATOR, sequenceName = "s_order", allocationSize = BaseRecord.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "ubrew_order")
public class OrderRecord extends BaseRecord {

    //@OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id")
    //private UserDetailRecord user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private final List<OrderItemRecord> items = new ArrayList<>();

    public OrderRecord() {
    }

//    public OrderRecord(UserDetailRecord user) {
//        this(null, user);
//    }

//    public OrderRecord(Long id, UserDetailRecord user) {
//        super(id);
//        this.user = user;
//    }
//
//    public UserDetailRecord getUser() {
//        return user;
//    }
//
//    public void setUser(UserDetailRecord user) {
//        this.user = user;
//    }

    public List<OrderItemRecord> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(OrderItemRecord orderItem) {
        Assert.notNull(orderItem, "orderItem is invalid [null]");
        Assert.isTrue(this.items.add(orderItem), String.format("orderItem [%d] was not added", orderItem.getId()));
    }

    public void removeItem(OrderItemRecord orderItem) {
        Assert.notNull(orderItem, "orderItem is invalid [null]");
        Assert.isTrue(this.items.remove(orderItem), String.format("orderItem [%d] was not removed", orderItem.getId()));
    }

    public void clearItems() {
        this.items.clear();
    }

//    @Override
//    public String toString() {
//        return "OrderRecord{" + "user=" + user + ", items=" + items + '}';
//    }

}
