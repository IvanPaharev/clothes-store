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
public class ColorController extends BaseController<Color, Integer> {
    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        super(colorService);
        this.colorService = colorService;
    }

}
