package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.DressService;
import com.netcracker.store.persistence.entity.*;
import com.netcracker.store.logic.dto.DressAndQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Created by A-one on 20.04.2017.
 */
@RestController
public class DressController {

    private byte[] multipartFile;

    @Autowired
    private DressService dressService;

    @RequestMapping(value = "/dress/", method = RequestMethod.GET)
    public ResponseEntity<List<Dress>> getAllDresses() {
        List<Dress> dresses = dressService.getAllEightTimes();
        if (dresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dresses, HttpStatus.OK);
    }

    @RequestMapping(value = "/color", method = RequestMethod.GET)
    public ResponseEntity<List<Color>> getAllColors() {
        List<Color> colors = dressService.getColors();
        if (colors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @RequestMapping(value = "/size", method = RequestMethod.GET)
    public ResponseEntity<List<Size>> getAllSizes() {
        List<Size> sizes = dressService.getSizes();
        if (sizes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sizes, HttpStatus.OK);
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ResponseEntity<List<Type>> getAllTypes() {
        List<Type> types = dressService.getTypes();
        if (types.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @RequestMapping(value = "/manufacturer", method = RequestMethod.GET)
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        List<Manufacturer> manufacturers = dressService.getManufacturers();
        if (manufacturers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

    @RequestMapping(value = "/dress/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dress> getDress(@PathVariable("id") Integer id) {
        System.out.println("Fetching User with id " + id);
        Dress dress = dressService.getDressById(id);
        if (dress == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dress, HttpStatus.OK);
    }

    @RequestMapping(value = "/dress/", method = RequestMethod.POST)
    public void createDress(@RequestBody Dress dress) {
        dressService.addDress(dress);
        String fileName = dress.getId() + ".jpg";
        try {
            Path path = Paths.get("D:\\dressStoreImages\\dress\\mainImages\\" + fileName);
            Files.write(path,multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/dress_img")
    public void uploadImg(@RequestParam("file")MultipartFile file) {
        try {
            multipartFile = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/dress/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Dress> updateDress(@RequestBody Dress dress) {
        dressService.updateDress(dress);
        return new ResponseEntity<Dress>(dress, HttpStatus.OK);
    }

    @RequestMapping(value = "/dress/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Dress> deleteDress(@PathVariable("id") Integer id) {
        dressService.deleteDress(id);
        return new ResponseEntity<Dress>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/addDressToBag", method = RequestMethod.POST)
    public void addDressToBag(@RequestBody DressAndQuantity dressAndQuantity) {
        dressService.addDressToBag(dressAndQuantity);
    }

    @RequestMapping(value = "/getUserBag", method = RequestMethod.GET)
    public ResponseEntity<List<DressAndQuantity>> getUserBag(){
        return new ResponseEntity<List<DressAndQuantity>>(dressService.getUserBag(), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteDressFromUserBag", method = RequestMethod.POST)
    public void deleteDressFromUserBag(@RequestBody DressAndQuantity dressAndQuantity) {
        dressService.deleteDressFromUserBag(dressAndQuantity);
    }
}
