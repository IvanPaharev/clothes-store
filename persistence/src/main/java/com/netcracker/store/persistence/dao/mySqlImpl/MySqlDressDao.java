package com.netcracker.store.persistence.dao.mySqlImpl;

import com.netcracker.store.persistence.dao.DressDao;
import com.netcracker.store.persistence.entity.Dress;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by A-one on 10.04.2017.
 */
@Component
public class MySqlDressDao extends MySqlBaseDao<Dress, Integer> implements DressDao {
    public MySqlDressDao() {
        super(Dress.class);
    }

    @Override
    public List<Dress> getDressesByType(String type) {
        Query query = getCurrentSession().createQuery("from Dress where type.name = :type");
        query.setParameter("type", type);
        return query.list();
    }
}
