package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by A-one on 19.04.2017.
 */
@Entity
@Table(name = "color")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class Color extends BaseEntity{

    private static final long serialVersionUID = 7137654951871026843L;

    @Column(unique = true)
    @Size(max = 60, message = "Color name max length - 60")
    @NotNull(message = "Color name cannot be null")
    private String color;

    @Column(name = "image_source")
    private String imageSource;

    @ManyToMany(mappedBy = "colorSet")
    @JsonIgnore
    private Set<Dress> dressSet;

    public Color() {
    }

    public Color(String color, String imageSource, Set<Dress> dressSet) {
        this.color = color;
        this.imageSource = imageSource;
        this.dressSet = dressSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color1 = (Color) o;

        return color != null ? color.equals(color1.color) : color1.color == null;

    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                ", imageSource='" + imageSource + '\'' +
                '}';
    }
}
