package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.BaseService;
import com.netcracker.store.persistence.entity.Category;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
public abstract class BaseController<E> {

    @Autowired
    private BaseService<E, ID> baseService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<E>> getAllCategories() {
        List<E> categories = baseService.getAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        baseService.add(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        baseService.update(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Integer id) {
        baseService.delete(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
