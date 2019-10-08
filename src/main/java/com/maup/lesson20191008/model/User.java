package com.maup.lesson20191008.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@ToString(of ={"firstName", "lastName","locale","basket"})
@Entity
@Table(name = "users")
public class User extends SystemDictionary {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "locale")
    private String locale;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "basket",
            joinColumns = @JoinColumn(
                    name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "goods_id",referencedColumnName = "id"))
    private Collection<Good> basket;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "addressUserId")
    private Set<Address> address;
}
