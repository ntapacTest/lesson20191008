package com.maup.lesson20191008.service;

import com.maup.lesson20191008.model.User;
import com.maup.lesson20191008.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public void save(User user) {
        User userEmail=userRepo.findUserByEmail(user.getEmail());
        if(userEmail==null) {
            userRepo.save(user);
        }
        else {
            System.out.println("User with this email " + user.getEmail()+" is already exists.");
        }
    }

    public void update(User user) {
        userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }
}