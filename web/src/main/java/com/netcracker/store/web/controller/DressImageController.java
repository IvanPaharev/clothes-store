package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.DressImageService;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.DressImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@RestController
@RequestMapping(value = "/dressImage")
public class DressImageController extends BaseController<DressImage, Integer> {

    private final DressImageService dressImageService;

    @Autowired
    public DressImageController(DressImageService dressImageService) {
        super(dressImageService);
        this.dressImageService = dressImageService;
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public void uploadMainImage(@RequestParam("file") MultipartFile file) {
        dressImageService.uploadMainImage(file);
    }

    @RequestMapping(value = "/other/{id}", method = RequestMethod.POST)
    public void uploadOtherImages(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) {
        dressImageService.uploadOtherImage(file, id);
    }
}
