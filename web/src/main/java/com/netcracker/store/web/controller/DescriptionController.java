package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.DescriptionService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/description")
public class DescriptionController {
    private final DescriptionService descriptionService;

    @Autowired
    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Description>> getAllCategories() {
        List<Description> categories = descriptionService.getAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        descriptionService.add(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        descriptionService.update(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Integer id) {
        descriptionService.delete(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
