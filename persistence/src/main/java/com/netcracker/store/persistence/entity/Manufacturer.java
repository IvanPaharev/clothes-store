package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by A-one on 18.04.2017.
 */
@Entity
@Table(name = "manufacturer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class Manufacturer extends BaseEntity {
    private static final long serialVersionUID = 7722192986659845970L;

    @Column(unique = true)
    @NotNull(message = "Manufacturer name cannot be null")
    @Size(max = 90, message = "Max manufacturer name length - 90 symbols")
    private String name;

    @Size(max = 90, message = "Max manufacturer description length - 600 symbols")
    private String description;

    @OneToMany(mappedBy = "manufacturer")
    @JsonIgnore
    private List<Dress> dresses;

    public Manufacturer() {
        super();
    }

    public Manufacturer(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "role='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return dresses != null ? dresses.equals(that.dresses) : that.dresses == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
