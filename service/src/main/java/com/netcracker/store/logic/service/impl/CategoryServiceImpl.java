package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.persistence.dao.CategoryDao;
import com.netcracker.store.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<Category, Integer> implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }
}
