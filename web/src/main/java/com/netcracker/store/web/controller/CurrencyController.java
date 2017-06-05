package com.netcracker.store.web.controller;

import com.netcracker.store.web.webservice.CurrencyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by A-one on 02.06.2017.
 */
@RestController
@RequestMapping(value = "/currencyInfo")
public class CurrencyController {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<CurrencyInfo> getCurrencyInfo(@Autowired RestTemplate restTemplate) {
        return new ResponseEntity<>(
                restTemplate.getForObject("http://api.fixer.io/latest?base=USD", CurrencyInfo.class),
                HttpStatus.OK
        );
    }
}
