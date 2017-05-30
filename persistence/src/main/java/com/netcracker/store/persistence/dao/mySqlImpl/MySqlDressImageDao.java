package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.DressImageDao;
import com.netcracker.store.persistence.entity.DressImage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public class MySqlDressImageDao extends MySqlBaseDao<DressImage, Integer> implements DressImageDao {
    public MySqlDressImageDao() {
        super(DressImage.class);
    }
}
