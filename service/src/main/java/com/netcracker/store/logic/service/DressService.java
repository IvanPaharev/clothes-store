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
    Dress getDressById(int id);
    void addDress(Dress dress);
    void deleteDress(int id);
    void updateDress(Dress dress);
    List<Dress> getDressesByCriteria(Criteria criteria, String type);
    Long getQueryCount(Criteria criteria, String type);
}
