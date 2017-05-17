package com.netcracker.store.persistence.dao;

import com.netcracker.store.persistence.entity.Dress;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by A-one on 10.04.2017.
 */
@Component
public interface DressDao extends BaseDao<Dress, Integer> {
    List<Dress> getDressesByType(String type);
}
