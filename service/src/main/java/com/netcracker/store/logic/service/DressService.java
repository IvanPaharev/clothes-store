package com.netcracker.store.logic.service;

import com.netcracker.store.persistence.dto.Criteria;
import com.netcracker.store.persistence.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by A-one on 20.04.2017.
 */
@Service
public interface DressService extends BaseService<Dress, Integer> {
    List<Dress> getAllDresses();
    List<Dress> getDressesByType(String type);
    Dress getDressWithDetailsById(int id);
    List<Dress> getDressesByCriteria(Criteria criteria);
    Long getQueryCount(Criteria criteria);
}
