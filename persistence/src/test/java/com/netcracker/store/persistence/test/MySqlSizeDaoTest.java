package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.BaseEntity;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        size.setUk(newName);
        getDao().update(size);
        assertEquals(newName, size.getUk());
    }

    @Override
    protected BaseDao getDao() {
        return sizeDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new Size("test", 2, "test", 2, 2, 2, 2, 2, new ArrayList<>(dressDao.getAll()));
    }

    @Override
    protected Collection<BaseEntity> getCollection() {
        return Arrays.asList(new Size("q", 1, "q", 1, 1, 1, 1, 1, new ArrayList<>(dressDao.getAll())),
                new Size("w", 3, "w", 3, 3, 3, 3, 0, new ArrayList<>(dressDao.getAll())));
    }
}
