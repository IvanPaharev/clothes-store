package com.netcracker.store.persistence.dao;

import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Dress;
import com.netcracker.store.persistence.entity.Manufacturer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by A-one on 10.04.2017.
 */
@Component
public interface DressDao extends BaseDao<Dress, Integer> {
    List<Dress> getDressesByType(String type);
    List<Dress> getAllByCriteria(List<Category> categories,
                                 List<Manufacturer> manufacturers,
                                 double priceFrom,
                                 double priceTo,
                                 String type);
}
