package com.netcracker.store.persistence.test.suite;

import com.netcracker.store.persistence.test.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by A-one on 10.04.2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        MySqlCategoryDaoTest.class,
        MySqlDressDaoTest.class,
        MySqlTypeDaoTest.class,
        MySqlManufacturerDaoTest.class,
        MySqlColorDaoTest.class,
        MySqlDescriptionDaoTest.class,
        MySqlDressImageDaoTest.class,
        MySqlSizeDaoTest.class,
        MySqlOrderDetailDaoTest.class,
        MySqlOrderStatusDaoTest.class,
        MySqlRoleDaoTest.class,
        MySqlUserDaoTest.class,
        MySqlUserOrderDaoTest.class
})
public class MySqlDaoTestSuite {
}
