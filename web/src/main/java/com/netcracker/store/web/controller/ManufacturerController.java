package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.ManufacturerService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/manufacturer")
public class ManufacturerController extends BaseController<Manufacturer, Integer> {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        super(manufacturerService);
        this.manufacturerService = manufacturerService;
    }

}
