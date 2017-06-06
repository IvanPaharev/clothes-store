package com.netcracker.store.persistence.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;
import java.util.List;
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
    @NotNull(message = "User email cannot be null")
    @Pattern(
            regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Invalid email: not up to standard!"
    )
    @Size(min = 6, max = 255, message = "Invalid email: too short!")
    private String email;

    @NotNull(message = "User password cannot be null")
    private String password;

    @Size(max = 200, message = "Invalid address: too long!")
    private String address;

    @Size(max = 35, message = "Invalid firstname: too long!")
    private String firstname;

    @Size(max = 35, message = "Invalid lastname: too long!")
    private String lastname;

    @Size(max = 20, message = "Invalid phone: too long!")
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<UserOrder> userOrders;

    @ManyToMany
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

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
