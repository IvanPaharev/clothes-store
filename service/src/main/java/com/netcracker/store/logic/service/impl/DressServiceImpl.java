package com.netcracker.store.logic.service.impl;

import com.netcracker.store.persistence.dto.Criteria;
import com.netcracker.store.persistence.dto.Sort;
import com.netcracker.store.logic.service.DressService;
import com.netcracker.store.persistence.dao.*;
import com.netcracker.store.persistence.entity.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by A-one on 20.04.2017.
 */
@Service
@Transactional
public class DressServiceImpl extends BaseServiceImpl<Dress, Integer> implements DressService {
    private final DressDao dressDao;
    private final DescriptionDao descriptionDao;

    public DressServiceImpl(DressDao dressDao,
                            DescriptionDao descriptionDao) {
        super(dressDao);
        this.dressDao = dressDao;
        this.descriptionDao = descriptionDao;

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
    public Dress getDressWithDetailsById(int id) {
        Dress dress = dressDao.get(id);
        Hibernate.initialize(dress.getColors());
        Hibernate.initialize(dress.getSizes());
        Hibernate.initialize(dress.getDressImages());
        return dress;
    }

    @Override
    public Dress add(Dress dress) {
        dress = dressDao.add(dress);
        dress.setImageSource(dress.getId() + ".jpg");
        Description description = dress.getDescription();
        description.setDressId(dress.getId());
        description.setDress(dress);
        descriptionDao.add(description);
        return dress;
    }

    @Override
    public List<Dress> getDressesByCriteria(Criteria criteria) {
        if (criteria.getSort() == null) {
            criteria.setSort(new Sort("", "releaseDate", false));
        }
        return dressDao.getAllByCriteria(criteria);
    }

    @Override
    public Long getQueryCount(Criteria criteria) {
        return dressDao.getQueryCount(criteria);
    }
}
