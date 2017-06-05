package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import com.netcracker.store.persistence.entity.Color;
import com.netcracker.store.persistence.entity.Dress;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by A-one on 10.04.2017.
 */
public class MySqlColorDaoTest extends BaseDaoGenericTest{

    @Autowired
    @Resource(name = "mySqlColorDao")
    private BaseDao<Color, Integer> colorDao;

    @Autowired
    @Resource(name = "mySqlDressDao")
    private BaseDao<Dress, Integer> dressDao;


    @Override
    public void testUpdate() {
        String newName = "newCategory";
        Color color = (Color) getDao().get(testId);
        color.setColor(newName);
        getDao().update(color);
        assertEquals(newName, color.getColor());
    }

    @Override
    protected BaseDao getDao() {
        return colorDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new Color("test category", "test res", new HashSet<>(dressDao.getAll()));
    }

    @Override
    protected Collection<BaseEntity> getCollection() {
        return Arrays.asList(new Color("q", "q", new HashSet<>(dressDao.getAll())),
                new Color("w", "w", new HashSet<>(dressDao.getAll())));
    }
}
