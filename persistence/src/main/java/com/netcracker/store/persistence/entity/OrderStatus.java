package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Created by A-one on 23.04.2017.
 */
@Entity
@Table(name = "order_status")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@Getter @Setter
public class OrderStatus extends BaseEntity{
    private static final long serialVersionUID = 272256990910779772L;

    @Column(unique = true)
    @NotNull(message = "Status cannot be null")
    @Size(max = 60, message = "Order status name max length - 60 symbols")
    private String status;

    @Size(max = 300, message = "Order status description max length - 300 symbols")
    private String description;

    @OneToMany(mappedBy = "orderStatus")
    @JsonIgnore
    private List<UserOrder> userOrders;

    public OrderStatus() {
        super();
    }

    public OrderStatus(String status, String description) {
        super();
        this.status = status;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
