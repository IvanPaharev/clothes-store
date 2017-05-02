package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.DressService;
import com.netcracker.store.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by A-one on 20.04.2017.
 */
@RestController
public class DressController {

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

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = dressService.getCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
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
        System.out.println("Creating User " + dress.getCategory().getName());

        System.out.println(dress.toString());
        dressService.addDress(dress);
    }

/*    @RequestMapping(value = "/dress/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Dress> updateDress(@PathVariable("id") Integer id, @RequestBody Dress dress) {
        System.out.println("Updating User " + id);

        Dress currentDress = dressService.getDressById(id);

        if (currentDress == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Dress>(HttpStatus.NOT_FOUND);
        }

        currentDress.setName(dress.getName());
        currentDress.setCategory(dress.getCategory());
        currentDress.setDescription(dress.getDescription());
        currentDress.setImagePath(dress.getImagePath());
        currentDress.setKind(dress.getKind());
        currentDress.setPrice(dress.getPrice());
        currentDress.setQuantity(dress.getQuantity());
        currentDress.setReleaseDate(dress.getReleaseDate());

        dressDao.update(currentDress);
        return new ResponseEntity<Dress>(currentDress, HttpStatus.OK);
    }

    @RequestMapping(value = "/dress/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Dress> deleteDress(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting User with id " + id);

        Dress dress = dressDao.get(id);
        if (dress == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Dress>(HttpStatus.NOT_FOUND);
        }

        dressDao.delete(id);
        return new ResponseEntity<Dress>(HttpStatus.NO_CONTENT);
    }*/
}
