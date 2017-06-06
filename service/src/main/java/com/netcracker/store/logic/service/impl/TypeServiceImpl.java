package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.TypeService;
import com.netcracker.store.persistence.dao.CategoryDao;
import com.netcracker.store.persistence.dao.TypeDao;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class TypeServiceImpl extends BaseServiceImpl<Type, Integer> implements TypeService {
    private final TypeDao typeDao;

    public TypeServiceImpl(TypeDao typeDao) {
        super(typeDao);
        this.typeDao = typeDao;
    }
}
