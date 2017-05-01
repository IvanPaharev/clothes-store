package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.SizeDao;
import com.netcracker.store.persistence.entity.Size;
import org.springframework.stereotype.Component;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlSizeDao extends MySqlBaseDao<Size, Integer> implements SizeDao {
    public MySqlSizeDao() {
        super(Size.class);
    }
}
