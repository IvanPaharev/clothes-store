package com.netcracker.store.logic.service;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by A-one on 30.05.2017.
 */
@Service
public interface BaseService<E, ID extends Serializable> {
    E add(E e);
    Collection<E> addAll(Collection<E> collection);
    E get(ID id);
    List<E> getAll();
    E update(E e);
    void delete(E e);
    void delete(ID id);
    void deleteAll(Collection<E> collection);
}
