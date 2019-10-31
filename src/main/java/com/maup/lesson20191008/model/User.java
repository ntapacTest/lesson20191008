package com.maup.lesson20191008.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@ToString(of = {"firstName", "lastName", "locale", "basket"})
@EqualsAndHashCode(of = {"firstName", "lastName", "googleId", "email", "id", "isActive"})
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "locale")
    private String locale;

    @Column(name = "userpic")
    private String userpic;

    @Column(name = "gender")
    private String gender;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date added;

    @Column(name = "isActive")
    private Boolean isActive = true;

    @Column(name = "role")
    private String role = "ROLE_USER";

    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "basket",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "goods_id", referencedColumnName = "id"))
    private Collection<Good> basket;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "addressUserId")
    Set<Address> address;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles= new ArrayList<>(Arrays.asList(role));
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return email;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return isActive;
    }

}