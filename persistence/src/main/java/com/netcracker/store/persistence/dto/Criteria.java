package com.netcracker.store.persistence.dto;

import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Manufacturer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A-one on 22.05.2017.
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Criteria {
    private List<Category> categories;
    private List<Manufacturer> manufacturers;
    private double priceFrom;
    private double priceTo;
    private Sort sort;
    private int pageNumber;
    private int pageSize;

    public Criteria() {
        categories = new ArrayList<>();
        manufacturers = new ArrayList<>();
    }

    public Criteria(List<Category> categories,
                    List<Manufacturer> manufacturers,
                    double priceFrom,
                    double priceTo,
                    int pageNumber,
                    int pageSize,
                    Sort sort) {
        this.categories = categories;
        this.manufacturers = manufacturers;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }
}
