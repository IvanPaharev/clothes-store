package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.DescriptionService;
import com.netcracker.store.persistence.dao.CategoryDao;
import com.netcracker.store.persistence.dao.DescriptionDao;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class DescriptionServiceImpl extends BaseServiceImpl<Description, Integer> implements DescriptionService {
    private final DescriptionDao descriptionDao;

    public DescriptionServiceImpl(DescriptionDao descriptionDao) {
        super(descriptionDao);
        this.descriptionDao = descriptionDao;
    }
}
