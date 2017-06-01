package com.netcracker.store.web.webservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * Created by A-one on 31.05.2017.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyInfo {
    private String base;
    private Date date;
    private Rates rates;
}
