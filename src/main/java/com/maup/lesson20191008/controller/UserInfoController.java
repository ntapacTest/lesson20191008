package com.maup.lesson20191008.controller;

import com.maup.lesson20191008.model.User;
import com.maup.lesson20191008.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
public class UserInfoController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getUserData(){
        log.info("select all users");
        return userService.findAll();
    }

}

