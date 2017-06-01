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
public class DescriptionController extends BaseController<Description, Integer> {
    private final DescriptionService descriptionService;

    @Autowired
    public DescriptionController(DescriptionService descriptionService) {
        super(descriptionService);
        this.descriptionService = descriptionService;
    }

}
