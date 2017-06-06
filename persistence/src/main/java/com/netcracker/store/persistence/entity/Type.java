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
@Table(name = "type")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class Type extends BaseEntity {
    private static final long serialVersionUID = 7722192986659845970L;

    @Column(unique = true)
    @NotNull(message = "Type name cannot be null")
    @Size(max = 45, message = "Type name max length - 45 symbols")
    private String name;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private List<Dress> dresses;

    public Type() {
        super();
    }

    public Type(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "role='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        if (name != null ? !name.equals(type.name) : type.name != null) return false;
        return dresses != null ? dresses.equals(type.dresses) : type.dresses == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        return result;
    }
}
