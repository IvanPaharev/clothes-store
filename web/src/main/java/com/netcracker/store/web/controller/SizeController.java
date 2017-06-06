package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.SizeService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/size")
public class SizeController extends BaseController<Size, Integer> {

    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        super(sizeService);
        this.sizeService = sizeService;
    }

}
