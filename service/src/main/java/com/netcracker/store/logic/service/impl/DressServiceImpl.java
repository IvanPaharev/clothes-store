package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.DressService;
import com.netcracker.store.persistence.dao.*;
import com.netcracker.store.persistence.entity.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by A-one on 20.04.2017.
 */
@Service
@Transactional
public class DressServiceImpl implements DressService {

    @Autowired
    @Resource(name = "mySqlDressDao")
    private DressDao dressDao;

    @Autowired
    @Resource(name = "mySqlManufacturerDao")
    private ManufacturerDao manufacturerDao;

    @Autowired
    @Resource(name = "mySqlCategoryDao")
    private CategoryDao categoryDao;

    @Autowired
    @Resource(name = "mySqlColorDao")
    private ColorDao colorDao;

    @Autowired
    @Resource(name = "mySqlSizeDao")
    private SizeDao sizeDao;

    @Autowired
    @Resource(name = "mySqlTypeDao")
    private TypeDao typeDao;

    @Autowired
    @Resource(name = "mySqlDescriptionDao")
    private DescriptionDao descriptionDao;

    @Override
    public List<Dress> getAllDresses(){
        List<Dress> dresses = dressDao.getAll();
        return dressDao.getAll();
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
        Description description = dress.getDescription();
        description.setDressId(dress.getId());
        description.setDress(dress);
        descriptionDao.add(description);
    }
}
