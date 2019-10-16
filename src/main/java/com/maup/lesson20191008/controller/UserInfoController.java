package com.maup.lesson20191008.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.maup.lesson20191008.model.User;
import com.maup.lesson20191008.model.UserView;
import com.maup.lesson20191008.pojo.UserDetailPojo;
import com.maup.lesson20191008.pojo.UserResponse;
import com.maup.lesson20191008.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.UnknownServiceException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
public class UserInfoController {

    @Autowired
    UserService userService;
    User user;

    @GetMapping("users")
    public UserResponse getUsersData() {
        log.info("select all users from UserDetailPojo");
        return new UserResponse(userService.findAll());
    }

    @GetMapping()
    public UserResponse getUserData(@AuthenticationPrincipal User user) throws UnknownServiceException {
        log.info("select user from UserDetailPojo");
        UserResponse userResponse=new UserResponse();
        userResponse.addUser(userService.findById(user.getId()));
        return userResponse;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("create")
    @JsonView({UserView.MainUserView.class})
    //@JsonView({UserView.MainUserView.class, SystemDictionaryView.MainSystemDictionaryView.class})
    public User createUser(@Valid @RequestBody UserDetailPojo userDetailPojo){
        //User user=new User();
        user.setFirstName(userDetailPojo.getFirstName());
        user.setLastName(userDetailPojo.getLastName());
        user.setEmail(userDetailPojo.getEmail());
        userService.save(user);
        return user;
    }

}

