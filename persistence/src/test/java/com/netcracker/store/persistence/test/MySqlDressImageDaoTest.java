package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import com.netcracker.store.persistence.entity.Color;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.DressImage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by A-one on 10.04.2017.
 */
public class MySqlDressImageDaoTest extends BaseDaoGenericTest{

    @Autowired
    @Resource(name = "mySqlDressImageDao")
    private BaseDao<DressImage, Integer> dressImageDao;

    @Autowired
    @Resource(name = "mySqlDressDao")
    private BaseDao<Dress, Integer> dressDao;

    @Override
    public void testUpdate() {
        String newName = "newCategory";
        DressImage dressImage = (DressImage) getDao().get(testId);
        dressImage.setImageSource(newName);
        getDao().update(dressImage);
        assertEquals(newName, dressImage.getImageSource());
    }

    @Override
    protected BaseDao getDao() {
        return dressImageDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new DressImage("test category", dressDao.getAll().get(0));
    }

    @Override
    protected Collection<BaseEntity> getCollection() {
        return Arrays.asList(new DressImage("test category", dressDao.getAll().get(0)),
                new DressImage("test", dressDao.getAll().get(0)));
    }
}
