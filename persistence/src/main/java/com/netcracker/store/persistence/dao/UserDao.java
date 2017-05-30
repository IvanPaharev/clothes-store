package com.netcracker.store.persistence.dao;

import com.netcracker.store.persistence.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by A-one on 19.04.2017.
 */
@Repository
public interface UserDao extends BaseDao<User, Integer> {
    User getUserByEmail(String email);
}
