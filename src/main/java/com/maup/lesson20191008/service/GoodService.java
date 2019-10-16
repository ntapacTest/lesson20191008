package com.maup.lesson20191008.service;

import com.maup.lesson20191008.model.Good;
import com.maup.lesson20191008.repo.GoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodService {

    private GoodRepo goodRepo;

    @Autowired
    public GoodService(GoodRepo goodRepo) {
        this.goodRepo = goodRepo;
    }

    public Optional<Good> findById(String id) {
        return goodRepo.findById(id);
    }

    public void save(Good good) {
        goodRepo.save(good);
    }

    public void update(Good good) {
        goodRepo.save(good);
    }

    public void delete(Good good) {
        goodRepo.delete(good);
    }

    public List<Good> findAll() {
        return goodRepo.findAll();
    }
}