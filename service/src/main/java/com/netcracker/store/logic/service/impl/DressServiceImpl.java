package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.DressService;
import com.netcracker.store.persistence.dao.DressDao;
import com.netcracker.store.persistence.entity.Dress;
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
        return dressDao.get(id);
    }
}
