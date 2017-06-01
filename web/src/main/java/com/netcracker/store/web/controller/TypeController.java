package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.TypeService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/type")
public class TypeController extends BaseController<Type, Integer> {

    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        super(typeService);
        this.typeService = typeService;
    }

}
