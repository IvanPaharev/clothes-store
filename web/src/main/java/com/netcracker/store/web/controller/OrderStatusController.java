package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.OrderStatusService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/orderStatus")
public class OrderStatusController extends BaseController<OrderStatus, Integer> {

    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        super(orderStatusService);
        this.orderStatusService = orderStatusService;
    }
}
