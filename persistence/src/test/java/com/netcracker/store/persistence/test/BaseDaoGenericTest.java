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

import java.util.List;

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
    private int deleteId;

    @Before
    public void setUp(){
        entity = getEntity();
        testId = ((BaseEntity)getDao().getAll().get(1)).getId();
    }

    @Test
    public void testGetAll() {
        List es = getDao().getAll();
        assertNotNull(es);
        assertEquals(false, es.isEmpty());
    }

    @Test
    public void testAdd() {
        getDao().add(entity);
        assertNotNull(getDao().get(entity.getId()));
        deleteId = entity.getId();
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
        getDao().delete(deleteId);
        assertNull(getDao().get(deleteId));
    }

    protected abstract BaseDao getDao();
    protected abstract BaseEntity getEntity();
}
