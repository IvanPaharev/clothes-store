package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by A-one on 23.04.2017.
 */
@Entity
@Table(name = "user_order_has_dress")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 5604812482204021100L;

    @EmbeddedId
    protected OrderDetailPK orderDetailPK;

    @NotNull
    @Min(value = 0)
    private int quantity;

    @JoinColumn(name = "dress_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Dress dress;

    @JoinColumn(name = "user_order_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    @ManyToOne
    private UserOrder userOrder;

    @JoinColumn(name = "color_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Color color;

    @JoinColumn(name = "size_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Size size;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetailPK orderDetailPK, int quantity) {
        this.orderDetailPK = orderDetailPK;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        if (quantity != that.quantity) return false;
        return orderDetailPK != null ? orderDetailPK.equals(that.orderDetailPK) : that.orderDetailPK == null;

    }

    @Override
    public int hashCode() {
        int result = orderDetailPK != null ? orderDetailPK.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }
}
