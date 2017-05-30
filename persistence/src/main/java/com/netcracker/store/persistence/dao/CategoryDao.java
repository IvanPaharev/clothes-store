package com.netcracker.store.persistence.dao;


import com.netcracker.store.persistence.entity.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public interface CategoryDao extends BaseDao<Category, Integer> {
}
