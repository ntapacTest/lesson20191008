package com.maup.lesson20191008.repo;

import com.maup.lesson20191008.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findUserByEmail(String email);

    @Query("select u from User u where u.email=:email and u.isActive=true")
    User findActiveByEmail(@Param("email") String email);

    @Query("select u from User u where u.lastName=:lastName and u.isActive=true")
    List<User> findActiveUserByLastName(@Param("lastName") String lastName);
}
