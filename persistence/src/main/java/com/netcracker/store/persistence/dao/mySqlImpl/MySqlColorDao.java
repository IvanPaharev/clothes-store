package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.ColorDao;
import com.netcracker.store.persistence.entity.Color;
import org.springframework.stereotype.Component;


/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlColorDao extends MySqlBaseDao<Color, Integer> implements ColorDao {
    public MySqlColorDao() {
        super(Color.class);
    }
}
