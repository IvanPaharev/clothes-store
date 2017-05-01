package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.TypeDao;
import com.netcracker.store.persistence.entity.Type;
import org.springframework.stereotype.Component;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlTypeDao extends MySqlBaseDao<Type, Integer> implements TypeDao {
    public MySqlTypeDao() {
        super(Type.class);
    }
}
