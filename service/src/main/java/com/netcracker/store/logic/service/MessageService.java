package com.netcracker.store.logic.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by A-one on 02.06.2017.
 */
@Service
public interface MessageService {
    Map<String, String> getMessages(String lang);
}
