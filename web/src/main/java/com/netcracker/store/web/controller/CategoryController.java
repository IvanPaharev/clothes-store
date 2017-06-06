package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.BaseService;
import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController extends BaseController<Category, Integer> {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }

}
