package com.maup.lesson20191008.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maup.lesson20191008.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
//@NoArgsConstructor
public class UserResponse extends ResourceSupport {
    @JsonProperty("users")
    public List<UserDetailPojo> users;

    @JsonIgnore
    @Autowired
    UserDetailPojo userDetailPojo;

    public void addUser(User user) {
        userDetailPojo = new UserDetailPojo();
        userDetailPojo.setId(user.getId());
        userDetailPojo.setFirstName(user.getFirstName());
        userDetailPojo.setLastName(user.getLastName());
        userDetailPojo.setEmail(user.getEmail());
        userDetailPojo.setUserPic(user.getUserPic());
        this.users.add(userDetailPojo);
    }

    public UserResponse(){
        users=new ArrayList<>();
    }

    public UserResponse(List<User> userList) {
        users=new ArrayList<>();

        userList
                .stream()
                .forEach(o->addUser(o));
//        this.users = userList
//                .stream()
//                .map(o -> {
//                    userDetailPojo = new UserDetailPojo();
//                    userDetailPojo.setId(o.getId());
//                    userDetailPojo.setFirstName(o.getFirstName());
//                    userDetailPojo.setLastName(o.getLastName());
//                    userDetailPojo.setEmail(o.getEmail());
//                    return userDetailPojo;
//                })
//                .collect(Collectors.toList());
    }
}