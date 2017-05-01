package com.netcracker.store.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by A-one on 10.04.2017.
 */
@MappedSuperclass
@ToString
@Getter @Setter
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1468284451392068139L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public BaseEntity() {
    }


}
