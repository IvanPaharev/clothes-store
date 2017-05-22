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

    @Column(name = "common", unique = true)
    @NotNull
    private String common;

    @Column(name = "uk", unique = true)
    @NotNull
    private Integer uk;

    @Column(name = "us", unique = true)
    @NotNull
    private String us;

    @Column(name = "italy", unique = true)
    @NotNull
    private Integer italy;

    @Column(name = "france", unique = true)
    @NotNull
    private Integer france;

    @Column(name = "russia", unique = true)
    @NotNull
    private Integer russia;

    @Column(name = "germany", unique = true)
    @NotNull
    private Integer germany;

    @Column(name = "japan", unique = true)
    @NotNull
    private Integer japan;

    @ManyToMany(mappedBy = "sizeSet")
    @JsonIgnore
    private Set<Dress> dressesSet;

    public Size() {
        super();
    }

    public Size(String common,
                Integer uk,
                String us,
                Integer italy,
                Integer france,
                Integer russia,
                Integer germany,
                Integer japan,
                Set<Dress> dressesSet) {
        this.common = common;
        this.uk = uk;
        this.us = us;
        this.italy = italy;
        this.france = france;
        this.russia = russia;
        this.germany = germany;
        this.japan = japan;
        this.dressesSet = dressesSet;
    }

    @Override
    public String toString() {
        return "Size{" +
                "common='" + common + '\'' +
                ", uk=" + uk +
                ", us='" + us + '\'' +
                ", italy=" + italy +
                ", france=" + france +
                ", russia=" + russia +
                ", germany=" + germany +
                ", japan=" + japan +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size size = (Size) o;

        if (!common.equals(size.common)) return false;
        if (!uk.equals(size.uk)) return false;
        if (!us.equals(size.us)) return false;
        if (!italy.equals(size.italy)) return false;
        if (!france.equals(size.france)) return false;
        if (!russia.equals(size.russia)) return false;
        if (!germany.equals(size.germany)) return false;
        return japan.equals(size.japan);
    }

    @Override
    public int hashCode() {
        int result = common.hashCode();
        result = 31 * result + uk.hashCode();
        result = 31 * result + us.hashCode();
        result = 31 * result + italy.hashCode();
        result = 31 * result + france.hashCode();
        result = 31 * result + russia.hashCode();
        result = 31 * result + germany.hashCode();
        result = 31 * result + japan.hashCode();
        return result;
    }
}
