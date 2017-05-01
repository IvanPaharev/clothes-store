package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import com.netcracker.store.persistence.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Created by A-one on 10.04.2017.
 */
public class MySqlTypeDaoTest extends BaseDaoGenericTest{

    @Autowired
    @Resource(name = "mySqlTypeDao")
    private BaseDao<Type, Integer> classDao;

    @Override
    public void testUpdate() {
        String newName = "newCategory";
        Type type = (Type) getDao().get(testId);
        type.setName(newName);
        getDao().update(type);
        assertEquals(newName, type.getName());
    }

    @Override
    protected BaseDao getDao() {
        return classDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new Type("test category");
    }
}
