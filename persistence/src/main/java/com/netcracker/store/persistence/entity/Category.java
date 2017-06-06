package com.netcracker.store.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "category")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class Category extends BaseEntity {
    private static final long serialVersionUID = 7722192986659845970L;

    @Column(unique = true)
    @Size(max = 60, message = "Category name max length - 60")
    @NotNull(message = "Category name cannot be null")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Dress> dresses;
    
    public Category() {
        super();
    }

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name != null ? name.equals(category.name) : category.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
