package com.maup.lesson20191008.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_address")
public class Address extends SystemDictionary {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User addressUserId;

    @Column(name = "address")
    @JsonView({AddressView.MainAddressView.class})
    private String address;

    @Column(name = "zip")
    @JsonView({AddressView.MainAddressView.class})
    private String zip;
}
