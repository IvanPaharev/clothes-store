package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.ManufacturerService;
import com.netcracker.store.persistence.dao.CategoryDao;
import com.netcracker.store.persistence.dao.ManufacturerDao;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class ManufacturerServiceImpl extends BaseServiceImpl<Manufacturer, Integer> implements ManufacturerService {
    private final ManufacturerDao manufacturerDao;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerDao manufacturerDao) {
        super(manufacturerDao);
        this.manufacturerDao = manufacturerDao;
    }
}
