package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.DressImageService;
import com.netcracker.store.persistence.dao.CategoryDao;
import com.netcracker.store.persistence.dao.DressImageDao;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.DressImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class DressImageServiceImpl extends BaseServiceImpl<DressImage, Integer> implements DressImageService {
    private final DressImageDao dressImageDao;

    @Autowired
    public DressImageServiceImpl(DressImageDao dressImageDao) {
        super(dressImageDao);
        this.dressImageDao = dressImageDao;
    }
}
