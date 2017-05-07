package com.netcracker.store.logic.dto;

import com.netcracker.store.persistence.entity.Dress;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by A-one on 07.05.2017.
 */
@EqualsAndHashCode
@ToString
@Getter @Setter
public class DressAndQuantity {
    private Dress dress;
    private Integer quantity;

    public DressAndQuantity() {
    }

    public DressAndQuantity(Dress dress, Integer quantity) {
        this.dress = dress;
        this.quantity = quantity;
    }
}
