package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.Resource;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by A-one on 10.04.2017.
 */
public class MySqlSizeDaoTest extends BaseDaoGenericTest{

    @Autowired
    @Resource(name = "mySqlSizeDao")
    private BaseDao<Size, Integer> sizeDao;

    @Autowired
    @Resource(name = "mySqlDressDao")
    private BaseDao<Dress, Integer> dressDao;


    @Override
    public void testUpdate() {
        Integer newName = 2;
        Size size = (Size) getDao().get(testId);
        size.setUkSize(newName);
        getDao().update(size);
        assertEquals(newName, size.getUkSize());
    }

    @Override
    protected BaseDao getDao() {
        return sizeDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new Size(2, new HashSet<>(dressDao.getAll()));
    }
}
