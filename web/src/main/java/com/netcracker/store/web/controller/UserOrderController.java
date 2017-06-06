package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.UserOrderService;
import com.netcracker.store.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/userOrder")
public class UserOrderController extends BaseController<UserOrder, Integer> {
    private final UserOrderService userOrderService;

    public UserOrderController(UserOrderService userOrderService) {
        super(userOrderService);
        this.userOrderService = userOrderService;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserOrder> add(UserOrder userOrder) {
        return new ResponseEntity<>(userOrderService.addUserOrder(), HttpStatus.OK);
    }

    @RequestMapping(value = "/bag", method = RequestMethod.POST)
    public void addDressToBag(@Valid @RequestBody OrderDetail orderDetail) {
        userOrderService.addToUserBag(orderDetail);
    }

    @RequestMapping(value = "/bag", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetail>> getUserBag(){
        return new ResponseEntity<>(userOrderService.getUserBag(), HttpStatus.OK);
    }

    @RequestMapping(value = "/bag", method = RequestMethod.PUT)
    public void deleteDressFromUserBag(@Valid @RequestBody OrderDetailPK orderDetailPK) {
        userOrderService.deleteDressFromUserBag(orderDetailPK);
    }
}
