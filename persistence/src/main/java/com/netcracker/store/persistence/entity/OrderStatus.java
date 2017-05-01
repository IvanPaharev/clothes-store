package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    private String status;

    private String description;

    @OneToMany(mappedBy = "orderStatus")
    @JsonIgnore
    private Set<UserOrder> userOrderSet;

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
