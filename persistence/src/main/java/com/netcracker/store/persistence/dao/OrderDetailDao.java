package com.netcracker.store.persistence.dao;

import com.netcracker.store.persistence.entity.OrderDetail;
import com.netcracker.store.persistence.entity.OrderDetailPK;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by A-one on 19.04.2017.
 */
@Repository
public interface OrderDetailDao extends BaseDao<OrderDetail, OrderDetailPK> {
}
