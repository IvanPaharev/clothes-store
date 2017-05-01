package com.netcracker.store.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "category")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class Category extends BaseEntity {

    private static final long serialVersionUID = 7722192986659845970L;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Dress> dressSet;
    
    public Category() {
        super();
    }

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "role='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return dressSet != null ? dressSet.equals(category.dressSet) : category.dressSet == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        return result;
    }
}
