package com.netcracker.store.logic.service;

import com.netcracker.store.persistence.entity.Dress;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by A-one on 20.04.2017.
 */
@Service
public interface DressService {
    List<Dress> getAllDresses();
    List<Dress> getAllEightTimes();
    Dress getDressById(int id);

}
