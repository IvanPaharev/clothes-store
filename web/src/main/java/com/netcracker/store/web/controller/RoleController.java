package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.RoleService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController<Role, Integer> {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        super(roleService);
        this.roleService = roleService;
    }

}
