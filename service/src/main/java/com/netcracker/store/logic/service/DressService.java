package com.netcracker.store.logic.service;

import com.netcracker.store.persistence.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by A-one on 20.04.2017.
 */
@Service
public interface DressService {
    List<Dress> getAllDresses();
    List<Dress> getAllEightTimes();
    Dress getDressById(int id);
    List<Manufacturer> getManufacturers();
    List<Category> getCategories();
    List<Color> getColors();
    List<Size> getSizes();
    List<Type> getTypes();
    void addDress(Dress dress);
    void deleteDress(int id);
    void updateDress(Dress dress);
}
