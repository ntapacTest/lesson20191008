package com.maup.lesson20191008.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.maup.lesson20191008.model.*;
import com.maup.lesson20191008.pojo.AddressPojo;
import com.maup.lesson20191008.service.AddressService;
import com.maup.lesson20191008.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.UnknownServiceException;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/api/address", produces = "application/json")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @GetMapping
    //@JsonView({AddressView.MainAddressView.class, SystemDictionaryView.MainSystemDictionaryView.class})
    @JsonView({AddressView.MainAddressView.class})
    public List<Address> getAddressData(){
        log.info("select all addresses");
        return addressService.findAll();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping()
    //@JsonView({AddressView.MainAddressView.class, SystemDictionaryView.MainSystemDictionaryView.class})
    @JsonView({AddressView.MainAddressView.class})
    public Address createAddress(@Valid @RequestBody AddressPojo addressPojo) throws UnknownServiceException {
        User user=userService
                .findById(addressPojo.getUserId());
        Address address=new Address();
        address.setAddress(addressPojo.getAddress());
        address.setZip(addressPojo.getZip());
        address.setAddressUserId(user);
        addressService.save(address);
        return address;
    }

}
