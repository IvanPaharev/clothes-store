package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.entity.Role;
import com.netcracker.store.persistence.dao.RoleDao;
import org.springframework.stereotype.Component;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlRoleDao extends MySqlBaseDao<Role, Integer> implements RoleDao {
    public MySqlRoleDao() {
        super(Role.class);
    }
}
