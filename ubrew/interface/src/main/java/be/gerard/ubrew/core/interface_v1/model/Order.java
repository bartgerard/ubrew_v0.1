package be.gerard.ubrew.core.interface_v1.model;

import be.gerard.core.interface_v1.model.User;

import java.io.Serializable;
import java.util.*;

/**
 * Order
 *
 * @author Bart Gerard
 */
public class Order implements Serializable {

    private Long id;

    private User user;

    private Date timestamp;

    private final List<OrderItem> items = new ArrayList<>();

    public Order(User user, Date timestamp) {
        this(null, user, timestamp);
    }

    public Order(Long id, User user, Date timestamp) {
        this.id = id;
        this.user = user;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(OrderItem orderItem) {
        this.items.add(orderItem);
    }

    public void removeItem(OrderItem orderItem) {
        this.items.remove(orderItem);
    }

    public void clearItems() {
        this.items.clear();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.user);
        hash = 79 * hash + Objects.hashCode(this.timestamp);
        hash = 79 * hash + Objects.hashCode(this.items);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return Objects.equals(this.items, other.items);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", user=" + user + ", timestamp=" + timestamp + ", items=" + items + '}';
    }

}
