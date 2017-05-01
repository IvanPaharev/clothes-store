package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.entity.OrderDetail;
import com.netcracker.store.persistence.entity.OrderDetailPK;
import com.netcracker.store.persistence.dao.OrderDetailDao;
import org.springframework.stereotype.Component;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlOrderDetailDao extends MySqlBaseDao<OrderDetail, OrderDetailPK> implements OrderDetailDao {
    public MySqlOrderDetailDao() {
        super(OrderDetail.class);
    }
}
