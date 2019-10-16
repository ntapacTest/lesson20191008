package com.maup.lesson20191008.service;

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

    public User findById(String id) throws UnknownServiceException {
        return userRepo.findById(id).orElseThrow(UnknownServiceException::new);
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