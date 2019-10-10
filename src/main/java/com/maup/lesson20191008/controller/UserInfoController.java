package com.maup.lesson20191008.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.maup.lesson20191008.model.AddressView;
import com.maup.lesson20191008.model.SystemDictionaryView;
import com.maup.lesson20191008.model.User;
import com.maup.lesson20191008.model.UserView;
import com.maup.lesson20191008.pojo.UserPojo;
import com.maup.lesson20191008.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
public class UserInfoController {

    @Autowired
    UserService userService;

    @GetMapping
    @JsonView({UserView.MainUserView.class})
    //@JsonView({UserView.MainUserView.class, SystemDictionaryView.MainSystemDictionaryView.class})
    public List<User> getUserData(){
        log.info("select all users");
        return userService.findAll();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("create")
    @JsonView({UserView.MainUserView.class})
    //@JsonView({UserView.MainUserView.class, SystemDictionaryView.MainSystemDictionaryView.class})
    public User createUser(@Valid @RequestBody UserPojo userPojo){
        User user=new User();
        user.setFirstName(userPojo.getFirstName());
        user.setLastName(userPojo.getLastName());
        user.setEmail(userPojo.getEmail());
        userService.save(user);
        return user;
    }

}

