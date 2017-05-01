package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import com.netcracker.store.persistence.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Created by A-one on 10.04.2017.
 */
public class MySqlManufacturerDaoTest extends BaseDaoGenericTest{

    @Autowired
    @Resource(name = "mySqlManufacturerDao")
    private BaseDao<Manufacturer, Integer> manufacturerDao;

    @Override
    public void testUpdate() {
        String newName = "newCategory";
        Manufacturer manufacturer = (Manufacturer) getDao().get(testId);
        manufacturer.setName(newName);
        getDao().update(manufacturer);
        assertEquals(newName, manufacturer.getName());
    }

    @Override
    protected BaseDao getDao() {
        return manufacturerDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new Manufacturer("test category", "test descr");
    }
}
