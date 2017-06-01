package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.BaseService;
import com.netcracker.store.persistence.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by A-one on 30.05.2017.
 */
@Service
@Transactional
public abstract class BaseServiceImpl<E, ID extends Serializable> implements BaseService<E, ID> {
    private final BaseDao<E, ID> baseDao;

    public BaseServiceImpl(BaseDao<E, ID> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void add(E e) {
        baseDao.add(e);
    }

    @Override
    public void addAll(Collection<E> collection) {
        baseDao.addAll(collection);
    }

    @Override
    public E get(ID id) {
        return baseDao.get(id);
    }

    @Override
    public List<E> getAll() {
        return baseDao.getAll();
    }

    @Override
    public void update(E e) {
        baseDao.update(e);
    }

    @Override
    public void delete(E e) {
        baseDao.delete(e);
    }

    @Override
    public void delete(ID id) {
        baseDao.delete(id);
    }

    @Override
    public void deleteAll(Collection<E> collection) {
        baseDao.deleteAll(collection);
    }
}
