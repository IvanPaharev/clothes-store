package com.netcracker.store.persistence.dao;

import com.netcracker.store.persistence.dto.Criteria;
import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.Manufacturer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by A-one on 10.04.2017.
 */
@Repository
public interface DressDao extends BaseDao<Dress, Integer> {
    List<Dress> getDressesByType(String type);
    List<Dress> getAllByCriteria(Criteria criteria);
    long getQueryCount(Criteria criteria);
}
