package com.netcracker.store.persistence.test;

import com.netcracker.store.persistence.dao.BaseDao;
import com.netcracker.store.persistence.dao.ColorDao;
import com.netcracker.store.persistence.dao.DressDao;
import com.netcracker.store.persistence.dto.Criteria;
import com.netcracker.store.persistence.dto.Sort;
import com.netcracker.store.persistence.entity.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

import static org.junit.Assert.assertEquals;




public class MySqlDressDaoTest extends BaseDaoGenericTest {

    @Autowired
    private DressDao dressDao;

    @Autowired
    @Resource(name = "mySqlCategoryDao")
    private BaseDao<Category, Integer> categoryDao;

    @Autowired
    @Resource(name = "mySqlTypeDao")
    private BaseDao<Type, Integer> classDao;

    @Autowired
    private ColorDao colorDao;

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

    @Test
    public void testCriteria() {
        System.out.println("hello");
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(manufacturerDao.getAll().get(1));
        manufacturers.add(manufacturerDao.getAll().get(6));
        List<Dress> dresses = dressDao.getAllByCriteria(
                new Criteria(
                        "women",
                categoryDao.getAll(),
                manufacturers,
                sizeDao.getAll(),
                colorDao.getAll().get(0),
                550,
                900,
                1,
                2,
                 new Sort("", "price", true)));
        for (Dress dress : dresses) {
            System.out.println(dress.getPrice());
            System.out.println(dresses.size());
        }
    }

    @Test
    public void testGetQueryCount(){
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(manufacturerDao.getAll().get(1));
        manufacturers.add(manufacturerDao.getAll().get(6));
        System.out.println(dressDao.getQueryCount(new Criteria(
                "women",
                        categoryDao.getAll(),
                        manufacturers,
                        sizeDao.getAll(),
                        colorDao.getAll().get(0),
                        550,
                        900,
                        1,
                        2,
                        new Sort("", "price", true))));
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
                new ArrayList<>(orderDetailDao.getAll()),
                new ArrayList<Size>(sizeDao.getAll()),
                new ArrayList<Color>(colorDao.getAll()),
                new ArrayList<DressImage>(dressImageDao.getAll())
        );
    }

    @Override
    protected Collection<BaseEntity> getCollection() {
        return Arrays.asList(getEntity(), getEntity());
    }
}
