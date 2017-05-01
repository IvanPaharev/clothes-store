package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.UserDao;
import com.netcracker.store.persistence.entity.User;
import org.springframework.stereotype.Component;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlUserDao extends MySqlBaseDao<User, Integer> implements UserDao {
    public MySqlUserDao() {
        super(User.class);
    }
}
