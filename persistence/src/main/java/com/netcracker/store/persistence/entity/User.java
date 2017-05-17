package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by A-one on 23.04.2017.
 */
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(callSuper = true)
@Getter @Setter
public class User extends BaseEntity {
    private static final long serialVersionUID = 4425799012842683419L;

    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    private String password;

    private String address;

    private String firstname;

    private String lastname;

    private String phone;

    @OneToMany(mappedBy = "user")
    private Set<UserOrder> userOrderSet;

    @ManyToMany
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet;

    public User() {
        super();
    }

    public User(String email,
                String password,
                String address,
                String firstname,
                String lastname,
                String phone) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }
}
