package com.netcracker.store.logic.test;

import com.netcracker.store.logic.config.ServiceConfig;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.logic.service.DressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
/**
 * Created by A-one on 10.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class DressServiceTest {

    @Autowired
    private DressService dressService;

    @Test
    public void testGetAllEightTimes() {
        assertEquals(dressService.getAllDresses().size() * 8, dressService.getAllEightTimes().size());
    }

    @Test
    public void testJson() {
        Dress dress = dressService.getAllDresses().get(1);
        System.out.println(dress.getCategory().getName());
    }
}
