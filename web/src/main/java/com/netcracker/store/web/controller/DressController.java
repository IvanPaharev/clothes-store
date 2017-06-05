package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.*;
import com.netcracker.store.persistence.dto.Criteria;
import com.netcracker.store.persistence.entity.*;
import com.netcracker.store.web.webservice.CurrencyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by A-one on 20.04.2017.
 */
@RestController
@RequestMapping(value = "/dress")
public class DressController extends BaseController<Dress, Integer>{
    private final DressService dressService;
    private final DressImageService dressImageService;

    @Autowired
    public DressController(DressService dressService,
                           DressImageService dressImageService) {
        super(dressService);
        this.dressService = dressService;
        this.dressImageService = dressImageService;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Dress> get(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(dressService.getDressWithDetailsById(id), HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Dress> add(@Valid @RequestBody Dress dress) {
        dress = dressService.add(dress);
        dressImageService.writeTemporalImages(dress);
        return new ResponseEntity<>(dress, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Dress> update(@Valid @RequestBody Dress dress) {
        dressImageService.writeTemporalImages(dress);
        return super.update(dress);
    }

    @RequestMapping(value = "/dressesByCriteria", method = RequestMethod.POST)
    public ResponseEntity<List<Dress>> getDressesByCriteria(@Valid @RequestBody Criteria criteria) {
        return new ResponseEntity<>(dressService.getDressesByCriteria(criteria), HttpStatus.OK);
    }

    @RequestMapping(value = "/queryCount", method = RequestMethod.POST)
    public ResponseEntity<Long> getQueryCount(@Valid @RequestBody Criteria criteria) {
        return new ResponseEntity<>(dressService.getQueryCount(criteria), HttpStatus.OK);
    }


}
