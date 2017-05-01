package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.config.DataConfig;
import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by A-one on 10.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@Rollback
@Transactional
public abstract class BaseDaoGenericTest {

    private BaseEntity entity;

    protected int testId;

    @Before
    public void setUp(){
        entity = getEntity();
        testId = ((BaseEntity)getDao().getAll().get(0)).getId();
    }

    @Test
    public void testAddAndGetAll() {
        int size = getDao().getAll().size();
        getDao().add(entity);
        assertTrue(size < getDao().getAll().size());
    }

    @Test
    public void testGet() {
        entity = (BaseEntity) getDao().get(testId);
        assertNotNull(entity);
    }

    @Test
    public abstract void testUpdate();

    @Test
    public void testDelete() {
        getDao().delete(testId);
        assertNull(getDao().get(testId));
    }

    protected abstract BaseDao getDao();
    protected abstract BaseEntity getEntity();
}
