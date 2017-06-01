package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.UserOrderService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.OrderDetail;
import com.netcracker.store.persistence.entity.User;
import com.netcracker.store.persistence.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/userOrder")
public class UserOrderController extends BaseController<UserOrder, Integer> {
    private final UserOrderService userOrderService;

    @Autowired
    public UserOrderController(UserOrderService userOrderService) {
        super(userOrderService);
        this.userOrderService = userOrderService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addUserOrder(){
        userOrderService.addUserOrder();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/addDressToBag", method = RequestMethod.POST)
    public void addDressToBag(@RequestBody OrderDetail orderDetail) {
        userOrderService.addToUserBag(orderDetail);
    }

    @RequestMapping(value = "/userBag", method = RequestMethod.GET)
    public ResponseEntity<Set<OrderDetail>> getUserBag(){
        return new ResponseEntity<>(userOrderService.getUserBag(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteDressFromUserBag", method = RequestMethod.POST)
    public void deleteDressFromUserBag(@RequestBody OrderDetail orderDetail) {
        userOrderService.deleteDressFromUserBag(orderDetail);
    }
}
