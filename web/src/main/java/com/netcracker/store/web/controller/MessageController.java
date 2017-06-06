package com.netcracker.store.web.controller;

import com.netcracker.store.logic.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by A-one on 01.06.2017.
 */
@RestController
@RequestMapping(value = "/messages")
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/{lang}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getMessage(@PathVariable String lang) {
        return new ResponseEntity<>(messageService.getMessages(lang), HttpStatus.OK);
    }
}
