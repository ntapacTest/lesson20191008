package com.maup.lesson20191008.service;

import com.maup.lesson20191008.exceptions.UserNotFoundException;
import com.maup.lesson20191008.model.User;
import com.maup.lesson20191008.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;
import java.util.List;

@Service
public class UserService {

    Logger logger= LoggerFactory.getLogger(UserService.class);
    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findById(Long id) throws UserNotFoundException {
        return userRepo.findById(id).orElseThrow(()->new UserNotFoundException("d"));
    }
    public User findByGoogleId(String googleId) throws  UserNotFoundException {
        return userRepo.findUserByGoogleId(googleId).orElseThrow(()->new UserNotFoundException("d"));
    }

    public User findUserByEmail(String email){
        return userRepo.findUserByEmail(email);
    }

    public void save(User user) {
        User userEmail=userRepo.findUserByEmail(user.getEmail());
        if(userEmail==null) {
            userRepo.save(user);
            logger.info("user: "+user.toString() +"added");
        }
        else {
            System.out.println("user with this email " + user.getEmail()+" is already exists");
            logger.info("user exist");
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