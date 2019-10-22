package com.maup.lesson20191008.repo;

import com.maup.lesson20191008.model.Address;
import com.maup.lesson20191008.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findUserByEmail(String email);

    Optional<User> findUserByGoogleId(String googleId);

    @Query("select u from User u where u.email=:email and u.isActive=true")
    User findActiveByEmail(@Param("email") String email);

    @Query("select u from User u where u.lastName=:lastName and u.isActive=true")
    List<User> findActiveUserByLastName(@Param("lastName") String lastName);

//    List<Address> findAddressesBy
}
