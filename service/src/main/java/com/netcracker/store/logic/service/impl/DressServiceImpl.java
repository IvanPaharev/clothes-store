package com.netcracker.store.logic.service.impl;

import com.netcracker.store.persistence.dto.Criteria;
import com.netcracker.store.persistence.dto.Sort;
import com.netcracker.store.logic.service.DressService;
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
public class DressServiceImpl extends BaseServiceImpl<Dress, Integer> implements DressService {

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
        super(dressDao);
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
    public Dress getDressById(int id) {
        Dress dress = dressDao.get(id);
        Hibernate.initialize(dress.getColorSet());
        Hibernate.initialize(dress.getSizeSet());
        Hibernate.initialize(dress.getDressImageSet());
        return dress;
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

    @Override
    public List<Dress> getDressesByCriteria(Criteria criteria, String type) {
        if (criteria.getCategories().size() == 0) {
            criteria.setCategories(categoryDao.getAll());
        }
        if (criteria.getManufacturers().size() == 0) {
            criteria.setManufacturers(manufacturerDao.getAll());
        }
        if (criteria.getSort() == null) {
            criteria.setSort(new Sort("", "releaseDate", false));
        }
        return dressDao.getAllByCriteria(criteria, type);
    }

    @Override
    public Long getQueryCount(Criteria criteria, String type) {
        if (criteria.getCategories().size() == 0) {
            criteria.setCategories(categoryDao.getAll());
        }
        if (criteria.getManufacturers().size() == 0) {
            criteria.setManufacturers(manufacturerDao.getAll());
        }
        return dressDao.getQueryCount(criteria, type);
    }
}
