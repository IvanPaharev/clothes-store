package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.DressImageDao;
import com.netcracker.store.persistence.entity.DressImage;
import org.springframework.stereotype.Component;

/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlDressImageDao extends MySqlBaseDao<DressImage, Integer> implements DressImageDao {
    public MySqlDressImageDao() {
        super(DressImage.class);
    }
}
