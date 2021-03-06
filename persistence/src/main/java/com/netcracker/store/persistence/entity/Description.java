package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import javax.validation.constraints.Size;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Created by A-one on 19.04.2017.
 */
@Entity
@Table(name = "description")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@DynamicInsert
public class Description implements Serializable{
    private static final long serialVersionUID = -4515287304435395433L;

    @Id
    @Column(name = "dress_id")
    private int dressId;

    @Size(max = 600, message = "Description max size - 600 symbols")
    private String english;

    @Size(max = 600, message = "Description max size - 600 symbols")
    private String russian;

    @OneToOne
    @JsonIgnore
    @PrimaryKeyJoinColumn
    private Dress dress;

    public Description() {
    }

    public Description(int dressId, String english, String russian, Dress dress) {
        this.dressId = dressId;
        this.english = english;
        this.russian = russian;
        this.dress = dress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        if (dressId != that.dressId) return false;
        if (english != null ? !english.equals(that.english) : that.english != null) return false;
        return russian != null ? russian.equals(that.russian) : that.russian == null;

    }

    @Override
    public int hashCode() {
        int result = dressId;
        result = 31 * result + (english != null ? english.hashCode() : 0);
        result = 31 * result + (russian != null ? russian.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Description{" +
                "dressId=" + dressId +
                ", english='" + english + '\'' +
                ", russian='" + russian + '\'' +
                '}';
    }
}
