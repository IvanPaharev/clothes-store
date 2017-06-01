package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.config.DataConfig;
import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.sql.Date;
import java.util.HashSet;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = DataConfig.class)
@Rollback
@Transactional
public class MySqlDescriptionDaoTest{

    @Autowired
    @Resource(name = "mySqlDescriptionDao")
    private BaseDao<Description, Integer> descriptionDao;

    @Autowired
    @Resource(name = "mySqlDressDao")
    private BaseDao<Dress, Integer> dressDao;

    @Autowired
    @Resource(name = "mySqlCategoryDao")
    private BaseDao<Category, Integer> categoryDao;

    @Autowired
    @Resource(name = "mySqlTypeDao")
    private BaseDao<Type, Integer> classDao;

    @Autowired
    @Resource(name = "mySqlManufacturerDao")
    private BaseDao<Manufacturer, Integer> manufacturerDao;

    @Autowired
    @Resource(name = "mySqlColorDao")
    private BaseDao<Color, Integer> colorDao;

    @Autowired
    @Resource(name = "mySqlSizeDao")
    private BaseDao<Size, Integer> sizeDao;

    @Autowired
    @Resource(name = "mySqlDressImageDao")
    private BaseDao<DressImage, Integer> dressImageDao;

    @Autowired
    @Resource(name = "mySqlOrderDetailDao")
    private BaseDao<OrderDetail, OrderDetailPK> orderDetailDao;

    @Test
    public void testUpdate() {
        String newName = "newCategory";
        Description description = (Description) descriptionDao.get(testId);
        description.setEnglish(newName);
        descriptionDao.update(description);
        assertEquals(newName, description.getEnglish());
    }

    private Description entity;
    protected int testId;

    @Before
    public void setUp(){
        entity = getEntity();
        testId = ((Description)descriptionDao.getAll().get(0)).getDressId();
    }

    @Test
    public void testAddAndGetAll() {
        int size = descriptionDao.getAll().size();
        descriptionDao.add(entity);
        System.out.println(entity.getDress());
        assertTrue(size < descriptionDao.getAll().size());
    }

    @Test
    public void testGet() {
        entity = descriptionDao.get(testId);
        assertNotNull(entity);
    }


    @Test
    public void testDelete() {
        descriptionDao.delete(testId);
        assertNull(descriptionDao.get(testId));
    }


    protected Description getEntity() {
        Dress dress =  new Dress(
                manufacturerDao.get(manufacturerDao.getAll().get(0).getId()),
                classDao.get(classDao.getAll().get(0).getId()),
                categoryDao.get(categoryDao.getAll().get(0).getId()),
                descriptionDao.get(descriptionDao.getAll().get(0).getDressId()),
                1.0,
                "test",
                new Date(System.currentTimeMillis()),
                new HashSet<>(orderDetailDao.getAll()),
                new HashSet<Size>(sizeDao.getAll()),
                new HashSet<Color>(colorDao.getAll()),
                new HashSet<DressImage>(dressImageDao.getAll())
        );
        dressDao.add(dress);
        return new Description(dress.getId(), "test category", "test res", dress);
    }
}
