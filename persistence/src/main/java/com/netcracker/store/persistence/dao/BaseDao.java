package com.netcracker.store.persistence.dao;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A-one on 10.04.2017.
 */
@Component
public interface BaseDao<E, ID extends Serializable> {
    void add(E e);
    E get(ID id);
    void update(E e);
    void delete(ID id);
    List<E> getAll();
}
