package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by A-one on 19.04.2017.
 */
@Entity
@Table(name = "dress_image")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class DressImage extends BaseEntity {

    private static final long serialVersionUID = -5879513953579091985L;

    @Column(name = "image_source")
    @NotNull
    private String imageSource;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dress_id", referencedColumnName = "id")
    @NotNull
    private Dress dress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DressImage that = (DressImage) o;

        if (imageSource != null ? !imageSource.equals(that.imageSource) : that.imageSource != null)
            return false;
        return dress != null ? dress.equals(that.dress) : that.dress == null;

    }

    @Override
    public int hashCode() {
        int result = imageSource != null ? imageSource.hashCode() : 0;
        return result;
    }

    public DressImage() {
    }

    public DressImage(String imageSource, Dress dress) {
        this.imageSource = imageSource;
        this.dress = dress;
    }

    @Override
    public String toString() {
        return "DressImage{" +
                "imageSource='" + imageSource + '\'' +
                '}';
    }
}
