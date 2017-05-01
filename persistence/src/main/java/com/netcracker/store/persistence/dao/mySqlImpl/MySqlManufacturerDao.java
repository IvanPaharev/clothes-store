package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.ManufacturerDao;
import com.netcracker.store.persistence.entity.Manufacturer;
import org.springframework.stereotype.Component;

/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlManufacturerDao extends MySqlBaseDao<Manufacturer, Integer> implements ManufacturerDao {
    public MySqlManufacturerDao() {
        super(Manufacturer.class);
    }
}
