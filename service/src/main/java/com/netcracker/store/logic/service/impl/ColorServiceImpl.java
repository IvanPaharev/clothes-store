package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.ColorService;
import com.netcracker.store.persistence.dao.ColorDao;
import com.netcracker.store.persistence.entity.Color;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
@Transactional
public class ColorServiceImpl extends BaseServiceImpl<Color, Integer> implements ColorService {
    private final ColorDao colorDao;

    public ColorServiceImpl(ColorDao colorDao) {
        super(colorDao);
        this.colorDao = colorDao;
    }
}
