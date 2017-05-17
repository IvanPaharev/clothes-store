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
import java.util.Set;

/**
 * Created by A-one on 19.04.2017.
 */
@Entity
@Table(name = "size")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class Size extends BaseEntity{

    private static final long serialVersionUID = 8101419755624978165L;

    @Column(name = "uk_size", unique = true)
    @NotNull
    private Integer ukSize;

    @ManyToMany(mappedBy = "sizeSet")
    @JsonIgnore
    private Set<Dress> dressesSet;

    public Size() {
        super();
    }

    public Size(Integer ukSize, Set<Dress> dressesSet) {
        this.ukSize = ukSize;
        this.dressesSet = dressesSet;
    }

    @Override
    public String toString() {
        return "Size{" +
                "ukSize=" + ukSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size size = (Size) o;

        return ukSize != null ? ukSize.equals(size.ukSize) : size.ukSize == null;

    }

    @Override
    public int hashCode() {
        int result = ukSize != null ? ukSize.hashCode() : 0;
        return result;
    }
}
