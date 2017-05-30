package com.netcracker.store.web.controller;

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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        categoryService.add(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        categoryService.update(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
