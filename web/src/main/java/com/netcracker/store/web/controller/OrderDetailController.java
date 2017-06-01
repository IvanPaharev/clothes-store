package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.OrderDetailService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.OrderDetail;
import com.netcracker.store.persistence.entity.OrderDetailPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/orderDetail")
public class OrderDetailController extends BaseController<OrderDetail, OrderDetailPK> {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        super(orderDetailService);
        this.orderDetailService = orderDetailService;
    }

}
