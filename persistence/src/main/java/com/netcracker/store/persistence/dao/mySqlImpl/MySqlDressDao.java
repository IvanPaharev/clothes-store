package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.DressDao;
import com.netcracker.store.persistence.entity.Dress;
import org.springframework.stereotype.Component;

/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlDressDao extends MySqlBaseDao<Dress, Integer> implements DressDao {
    public MySqlDressDao() {
        super(Dress.class);
    }

}
