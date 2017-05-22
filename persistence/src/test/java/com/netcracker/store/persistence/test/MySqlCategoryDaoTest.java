package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.dao.CategoryDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import com.netcracker.store.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Created by A-one on 10.04.2017.
 */
public class MySqlCategoryDaoTest extends BaseDaoGenericTest {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void testUpdate() {
        String newName = "newCategory";
        Category category = (Category) getDao().get(testId);
        category.setName(newName);
        getDao().update(category);
        assertEquals(newName, category.getName());
    }

    @Override
    protected BaseDao getDao() {
        return categoryDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new Category("test category");
    }
}
