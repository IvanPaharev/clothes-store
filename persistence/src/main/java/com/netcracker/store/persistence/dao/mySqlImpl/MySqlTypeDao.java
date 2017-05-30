package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.TypeDao;
import com.netcracker.store.persistence.entity.Type;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public class MySqlTypeDao extends MySqlBaseDao<Type, Integer> implements TypeDao {
    public MySqlTypeDao() {
        super(Type.class);
    }
}
