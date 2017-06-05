package com.netcracker.store.persistence.dto;

import com.netcracker.store.persistence.entity.Category;
import com.netcracker.store.persistence.entity.Color;
import com.netcracker.store.persistence.entity.Manufacturer;
import com.netcracker.store.persistence.entity.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
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
    private String type;
    private List<Category> categories;
    private List<Manufacturer> manufacturers;
    private List<Size> sizes;
    private Color color;
    private Sort sort;
    private int pageNumber;
    private int pageSize;

    @DecimalMin(value = "0.0", message = "Price cannot be lower than zero!")
    private double priceFrom;

    @DecimalMin(value = "0.0", message = "Price cannot be lower than zero!")
    private double priceTo;

    public Criteria() {
        categories = new ArrayList<>();
        manufacturers = new ArrayList<>();
        sizes = new ArrayList<>();
    }

    public Criteria(String type,
                    List<Category> categories,
                    List<Manufacturer> manufacturers,
                    List<Size> sizes,
                    Color color,
                    double priceFrom,
                    double priceTo,
                    int pageNumber,
                    int pageSize,
                    Sort sort) {
        this.type = type;
        this.categories = categories;
        this.manufacturers = manufacturers;
        this.sizes = sizes;
        this.color = color;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }
}
