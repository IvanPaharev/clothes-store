package com.netcracker.store.logic.service;

import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.OrderDetail;
import com.netcracker.store.persistence.entity.OrderDetailPK;
import org.springframework.stereotype.Service;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
public interface OrderDetailService extends BaseService<OrderDetail, OrderDetailPK> {
}
