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

    private byte[][] multipartFilesBytes = new byte[100][];
    private final DressService dressService;
    private final DressImageService dressImageService;

    @Autowired
    public DressController(DressService dressService,
                           DressImageService dressImageService) {
        super(dressService);
        this.dressService = dressService;
        this.dressImageService = dressImageService;
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public ResponseEntity<Dress> get(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(dressService.getDressWithDetailsById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "type/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<Dress>> getDressesByType(@PathVariable String type) {
        List<Dress> dresses = dressService.getDressesByType(type);
        if (dresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dresses, HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void createDress(@RequestBody Dress dress) {
        dressService.addDress(dress);
        int dressId = dress.getId();
        String fileName = dressId + ".jpg";
        try {
            Path path = Paths.get("D:\\dressStoreImages\\dress\\mainImages\\" + fileName);
            Files.write(path, multipartFilesBytes[0]);
            multipartFilesBytes[0] = null;
            for (int i = 1; i < multipartFilesBytes.length; i++) {
                if (multipartFilesBytes[i] != null) {
                    fileName = dressId + "_" + i + ".jpg";
                    path = Paths.get("D:\\dressStoreImages\\dress\\otherImages\\" + fileName);
                    Files.write(path, multipartFilesBytes[i]);
                    dressImageService.add(new DressImage(fileName, dress));
                }
                multipartFilesBytes[i] = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/image/{id}")
    public void uploadImg(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) {
        try {
            if (id < multipartFilesBytes.length) {
                multipartFilesBytes[id] = file.getBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/dressesByCriteria/{type}", method = RequestMethod.POST)
    public ResponseEntity<List<Dress>> getDressesByCreiteria(@RequestBody Criteria criteria, @PathVariable String type) {
        return new ResponseEntity<List<Dress>>(dressService.getDressesByCriteria(criteria, type), HttpStatus.OK);
    }

    @RequestMapping(value = "/getQueryCount/{type}", method = RequestMethod.POST)
    public ResponseEntity<Long> getQueryCount(@RequestBody Criteria criteria, @PathVariable String type) {
        return new ResponseEntity<Long>(dressService.getQueryCount(criteria, type), HttpStatus.OK);
    }

    @RequestMapping(value = "/currencyInfo", method = RequestMethod.GET)
    public ResponseEntity<CurrencyInfo> getCurrencyInfo(@Autowired RestTemplate restTemplate) {
        return new ResponseEntity<CurrencyInfo>(
                restTemplate.getForObject("http://api.fixer.io/latest?base=USD", CurrencyInfo.class),
                HttpStatus.OK
        );
    }
}
