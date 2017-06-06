package com.netcracker.store.logic.service.impl;

import com.netcracker.store.logic.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by A-one on 02.06.2017.
 */
@Service
@PropertySource("classpath:service.properties")
public class MessageServiceImpl implements MessageService {

    @Value("${bundleName}")
    private String bundleName;

    @Override
    public Map<String, String> getMessages(String lang) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName, new Locale(lang));
        Enumeration<String> keys = resourceBundle.getKeys();
        Map<String, String> messages = new HashMap<>();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            messages.put(key, resourceBundle.getString(key));
        }
        return messages;
    }
}
