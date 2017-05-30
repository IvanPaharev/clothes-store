package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.ColorService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/color")
public class ColorController {
    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Color>> getAllColors() {
        List<Color> colors = colorService.getAll();
        if (colors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Color> addColor(@RequestBody Color color) {
        colorService.add(color);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Color> updateCategory(@RequestBody Color color) {
        colorService.update(color);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Color> deleteColor(@PathVariable("id") Integer id) {
        colorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
