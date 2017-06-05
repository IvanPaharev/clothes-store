package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Created by A-one on 23.04.2017.
 */
@Entity
@Table(name = "user_order")
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
public class UserOrder extends BaseEntity {
    private static final long serialVersionUID = -8529336881202343860L;

    @Column(name = "date_created")
    @NotNull(message = "Date of user order creation cannot be null")
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    @NotNull(message = "Order status cannot be null")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    @NotNull(message = "User cannot be null")
    private User user;

    @OneToMany(mappedBy = "userOrder")
    private Set<OrderDetail> orderDetailSet;

    public UserOrder() {
        super();
    }

    public UserOrder(Date dateCreated,
                     OrderStatus orderStatus,
                     User user,
                     Set<OrderDetail> orderDetailSet) {
        super();
        this.dateCreated = dateCreated;
        this.orderStatus = orderStatus;
        this.user = user;
        this.orderDetailSet = orderDetailSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOrder userOrder = (UserOrder) o;

        if (dateCreated != null ? !dateCreated.equals(userOrder.dateCreated) : userOrder.dateCreated != null)
            return false;
        return orderStatus != null ? orderStatus.equals(userOrder.orderStatus) : userOrder.orderStatus == null;

    }

    @Override
    public int hashCode() {
        int result = dateCreated != null ? dateCreated.hashCode() : 0;
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }
}
