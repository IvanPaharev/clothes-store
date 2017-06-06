package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.CategoryService;
import com.netcracker.store.logic.service.SizeService;
import com.netcracker.store.persistence.dao.CategoryDao;
import com.netcracker.store.persistence.dao.SizeDao;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class SizeServiceImpl extends BaseServiceImpl<Size, Integer> implements SizeService {
    private final SizeDao sizeDao;

    public SizeServiceImpl(SizeDao sizeDao) {
        super(sizeDao);
        this.sizeDao = sizeDao;
    }
}
