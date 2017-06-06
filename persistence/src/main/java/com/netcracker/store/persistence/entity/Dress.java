package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "dress")
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class Dress extends BaseEntity {
    private static final long serialVersionUID = 3932117536853558680L;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    @NotNull(message = "Manufacturer cannot be null")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @NotNull(message = "Type cannot be null")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NotNull(message = "Category cannot be null")
    private Category category;

    @OneToOne(mappedBy = "dress")
    private Description description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", message = "Price cannot be lower than zero")
    private double price;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount cannot be lower than zero")
    private int amount;

    @Column(name = "image_source")
    private String imageSource;

    @Column(name = "release_date")
    private Date releaseDate;

    @OneToMany(mappedBy = "dress")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @ManyToMany
    @JoinTable(name = "dress_has_size",
            joinColumns = @JoinColumn(name = "dress_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    @NotNull(message = "Sizes cannot be null")
    private List<Size> sizes;

    @ManyToMany
    @JoinTable(name = "dress_has_color",
            joinColumns = @JoinColumn(name = "dress_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    @NotNull(message = "Colors cannot be null")
    private List<Color> colors;

    @OneToMany(mappedBy = "dress")
    private List<DressImage> dressImages;

    public Dress() {
        super();
    }

    public Dress(Manufacturer manufacturer,
                 Type type,
                 Category category,
                 Description description,
                 Double price,
                 String imageSource,
                 Date releaseDate,
                 List<OrderDetail> orderDetails,
                 List<Size> sizes,
                 List<Color> colors,
                 List<DressImage> dressImages) {
        this.manufacturer = manufacturer;
        this.type = type;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageSource = imageSource;
        this.releaseDate = releaseDate;
        this.orderDetails = orderDetails;
        this.sizes = sizes;
        this.colors = colors;
        this.dressImages = dressImages;
    }

    @Override
    public String toString() {
        return "Dress{" +
                "manufacturer=" + manufacturer +
                ", type=" + type +
                ", category=" + category +
                ", description=" + description +
                ", price=" + price +
                ", imageSource='" + imageSource + '\'' +
                ", releaseDate=" + releaseDate +
                ", orderDetails=" + orderDetails +
                ", sizes=" + sizes +
                ", colors=" + colors +
                ", dressImages=" + dressImages +
                '}';
    }
}
