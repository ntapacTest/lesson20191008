package com.maup.lesson20191008.repo;

import com.maup.lesson20191008.model.Address;
import com.maup.lesson20191008.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
//    Тоже работает
//    @Query(value = "SELECT * FROM USER_ADDRESS WHERE user_id = ?1", nativeQuery = true)
//    List<Address> findByUserId(Long userId);

    @Query(value = "SELECT * FROM USER_ADDRESS WHERE user_id = :userId", nativeQuery = true)
    List<Address> findByUserId(@Param("userId")Long userId);

    List<Address> findAddressesByAddressUserId(User user);

}
