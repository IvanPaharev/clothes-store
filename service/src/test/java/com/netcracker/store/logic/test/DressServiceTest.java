package com.netcracker.store.logic.test;

import com.netcracker.store.logic.config.ServiceConfig;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.logic.service.DressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
/**
 * Created by A-one on 10.04.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
@EnableConfigurationProperties
@ContextConfiguration(classes = ServiceConfig.class)
public class DressServiceTest {

    @Autowired
    private DressService dressService;


    @Test
    public void testJson() {
        Dress dress = dressService.getAllDresses().get(1);
        System.out.println(dress.getCategory().getName());
    }
}
