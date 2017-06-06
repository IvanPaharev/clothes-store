package com.netcracker.store.persistence.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by A-one on 23.05.2017.
 */


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Sort {
    private String name;
    private String parameter;
    private boolean asc;

    public Sort() {
    }

    public Sort(String name, String parameter, boolean asc) {
        this.name = name;
        this.parameter = parameter;
        this.asc = asc;
    }
}
