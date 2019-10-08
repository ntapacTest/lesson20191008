package com.maup.lesson20191008.service;

import com.maup.lesson20191008.model.Address;
import com.maup.lesson20191008.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private AddressRepo addressRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public Optional<Address> findById(Long id) {
        return addressRepo.findById(id);
    }

    public void save(Address address) {
        addressRepo.save(address);
    }

    public void update(Address address) {
        addressRepo.save(address);
    }

    public void delete(Address address) {
        addressRepo.delete(address);
    }

    public List<Address> findAll() {
        return addressRepo.findAll();
    }
}