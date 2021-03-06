/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.netcracker.store.persistence.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author markito
 */
@Embeddable
@Getter @Setter
@EqualsAndHashCode
@ToString
public class OrderDetailPK implements Serializable {
    private static final long serialVersionUID = -1381453765352891148L;

    @Column(name = "user_order_id")
    @NotNull(message = "User order id cannot be null")
    private int userOrderId;

    @Column(name = "dress_id")
    @NotNull(message = "Dress id cannot be null")
    private int dressId;

    @Column(name = "color_id")
    @NotNull(message = "Color id cannot be null")
    private int colorId;

    @Column(name = "size_id")
    @NotNull(message = "Size id cannot be null")
    private int sizeId;

    public OrderDetailPK() {
    }

    public OrderDetailPK(int userOrderId, int dressId, int colorId, int sizeId) {
        this.userOrderId = userOrderId;
        this.dressId = dressId;
        this.colorId = colorId;
        this.sizeId = sizeId;
    }
}
