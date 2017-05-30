package com.netcracker.store.persistence.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public interface BaseDao<E, ID extends Serializable> {
    void add(E e);
    void addAll(Collection<E> collection);
    E get(ID id);
    List<E> getAll();
    void update(E e);
    void delete(E e);
    void delete(ID id);
    void deleteAll(Collection<E> collection);
}
