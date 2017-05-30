package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.ManufacturerDao;
import com.netcracker.store.persistence.entity.Manufacturer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public class MySqlManufacturerDao extends MySqlBaseDao<Manufacturer, Integer> implements ManufacturerDao {
    public MySqlManufacturerDao() {
        super(Manufacturer.class);
    }
}
