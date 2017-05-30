package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.dao.CategoryDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public class MySqlCategoryDao extends MySqlBaseDao<Category, Integer> implements CategoryDao {
    public MySqlCategoryDao() {
        super(Category.class);
    }
}
