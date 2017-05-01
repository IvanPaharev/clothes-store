package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.DescriptionDao;
import com.netcracker.store.persistence.entity.Description;
import org.springframework.stereotype.Component;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlDescriptionDao extends MySqlBaseDao<Description, Integer> implements DescriptionDao {
    public MySqlDescriptionDao() {
        super(Description.class);
    }
}
