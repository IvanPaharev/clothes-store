package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.entity.UserOrder;
import com.netcracker.store.persistence.dao.UserOrderDao;
import org.springframework.stereotype.Component;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlUserOrderDao extends MySqlBaseDao<UserOrder, Integer> implements UserOrderDao {
    public MySqlUserOrderDao() {
        super(UserOrder.class);
    }
}
