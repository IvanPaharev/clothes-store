package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.RoleService;
import com.netcracker.store.persistence.dao.RoleDao;
import com.netcracker.store.persistence.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        super(roleDao);
        this.roleDao = roleDao;
    }
}
