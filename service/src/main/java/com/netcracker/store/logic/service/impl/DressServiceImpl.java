package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.dto.DressAndQuantity;
import com.netcracker.store.logic.service.DressService;
import com.netcracker.store.logic.service.UserOrderService;
import com.netcracker.store.persistence.dao.*;
import com.netcracker.store.persistence.entity.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by A-one on 20.04.2017.
 */
@Service
@Transactional
public class DressServiceImpl implements DressService {

    private final DressDao dressDao;
    private final ManufacturerDao manufacturerDao;
    private final CategoryDao categoryDao;
    private final ColorDao colorDao;
    private final SizeDao sizeDao;
    private final TypeDao typeDao;
    private final DescriptionDao descriptionDao;
    private final DressImageDao dressImageDao;

    @Autowired
    public DressServiceImpl(DressDao dressDao,
                            ManufacturerDao manufacturerDao,
                            CategoryDao categoryDao,
                            ColorDao colorDao,
                            SizeDao sizeDao,
                            TypeDao typeDao,
                            DescriptionDao descriptionDao,
                            DressImageDao dressImageDao) {
        this.dressDao = dressDao;
        this.manufacturerDao = manufacturerDao;
        this.categoryDao = categoryDao;
        this.colorDao = colorDao;
        this.sizeDao = sizeDao;
        this.typeDao = typeDao;
        this.descriptionDao = descriptionDao;
        this.dressImageDao = dressImageDao;
    }

    @Override
    public List<Dress> getAllDresses(){
        return dressDao.getAll();
    }

    @Override
    public List<Dress> getDressesByType(String type) {
        return dressDao.getDressesByType(type);
    }

    @Override
    public List<Dress> getAllEightTimes() {
        List<Dress> dresses = dressDao.getAll();
        for (int i = 0; i < 3; i++) {
            dresses.addAll(dresses);
        }
        return dresses;
    }

    @Override
    public Dress getDressById(int id) {
        Dress dress = dressDao.get(id);
        Hibernate.initialize(dress.getColorSet());
        Hibernate.initialize(dress.getSizeSet());
        Hibernate.initialize(dress.getDressImageSet());
        return dress;
    }

    @Override
    public List<Manufacturer> getManufacturers() {
        return manufacturerDao.getAll();
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.getAll();
    }

    @Override
    public List<Color> getColors() {
        return colorDao.getAll();
    }

    @Override
    public List<Size> getSizes() {
        return sizeDao.getAll();
    }

    @Override
    public List<Type> getTypes() {
        return typeDao.getAll();
    }

    @Override
    public void addDress(Dress dress) {
        dressDao.add(dress);
        dress.setImageSource(dress.getId() + ".jpg");
        Description description = dress.getDescription();
        description.setDressId(dress.getId());
        description.setDress(dress);
        descriptionDao.add(description);
    }

    @Override
    public void deleteDress(int id) {
        dressDao.delete(id);
    }

    @Override
    public void updateDress(Dress dress) {
        dressDao.update(dress);
    }

/*    @Override
    public void addDressToBag(DressAndQuantity dressAndQuantity) {
        userOrderService.addToUserBag(dressAndQuantity);
    }

    @Override
    public Set<OrderDetail> getUserBag() {
        return userOrderService.getUserBag();
    }

    @Override
    public void deleteDressFromUserBag(DressAndQuantity dressAndQuantity) {
        userBag.remove(dressAndQuantity);
    }*/

    @Override
    public void addDressImage(Dress dress, String fileName) {
        dressImageDao.add(new DressImage(fileName, dress));
    }
}
