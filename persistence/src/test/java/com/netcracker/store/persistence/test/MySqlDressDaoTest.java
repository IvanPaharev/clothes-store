package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.dao.DressDao;
import com.netcracker.store.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.Resource;
import java.sql.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;




public class MySqlDressDaoTest extends BaseDaoGenericTest {

    @Autowired
    @Resource(name = "mySqlDressDao")
    private DressDao dressDao;

    @Autowired
    @Resource(name = "mySqlCategoryDao")
    private BaseDao<Category, Integer> categoryDao;

    @Autowired
    @Resource(name = "mySqlTypeDao")
    private BaseDao<Type, Integer> classDao;

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
    @Resource(name = "mySqlDescriptionDao")
    private BaseDao<Description, Integer> descriptionDao;

    @Autowired
    @Resource(name = "mySqlManufacturerDao")
    private BaseDao<Manufacturer, Integer> manufacturerDao;

    @Autowired
    @Resource(name = "mySqlOrderDetailDao")
    private BaseDao<OrderDetail, OrderDetailPK> orderDetailDao;

    @Override
    public void testUpdate() {
        Double price = 5050.5;
        Dress dress = (Dress) getDao().get(testId);
        dress.setPrice(price);
        getDao().update(dress);
        assertEquals(price, (Double) dress.getPrice());
    }

    @Override
    protected BaseDao getDao() {
        return dressDao;
    }

    @Override
    protected BaseEntity getEntity() {
        return new Dress(
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
    }
}
