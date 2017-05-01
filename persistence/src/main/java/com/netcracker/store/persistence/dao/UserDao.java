package com.netcracker.store.persistence.dao;

import com.netcracker.store.persistence.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by A-one on 19.04.2017.
 */
@Component
public interface UserDao extends BaseDao<User, Integer> {
}
