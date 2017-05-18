package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.DressService;
import com.netcracker.store.logic.service.OrderStatusService;
import com.netcracker.store.logic.service.UserOrderService;
import com.netcracker.store.logic.service.UserService;
import com.netcracker.store.persistence.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping("/order")
public class PaymentController {

    @Autowired
    private UserOrderService userOrderService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Boolean> addUserOrder(){
        userOrderService.addUserOrder();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
