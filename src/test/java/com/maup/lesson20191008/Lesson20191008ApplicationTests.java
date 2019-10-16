package com.maup.lesson20191008;

import com.maup.lesson20191008.model.User;
import com.maup.lesson20191008.repo.UserRepo;
import com.maup.lesson20191008.service.AddressService;
import com.maup.lesson20191008.service.GoodService;
import com.maup.lesson20191008.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.net.UnknownServiceException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {Lesson20191008Application.class})
public class Lesson20191008ApplicationTests {

    @Autowired
    AddressService addressService;

    @Autowired
    GoodService goodService;

    @Autowired
    UserService userService;



    @Test
    @Transactional
    @Commit
    public void testAddUserCrudRepository(){
        User anton = new User();
        anton.setFirstName("Ivan");
        anton.setLastName("Ivanov");
        anton.setLocale("ua");
        anton.setEmail("testmail2@mail2.com");
        userService.save(anton);
    }

    @Test
    @Transactional
    public void testUserCrudRepository() throws UnknownServiceException {
        User empOpt=userService.findById("1l");
        System.out.println(empOpt.getFirstName() + " " + empOpt.getLastName());
    }

    @Test
    public void contextLoads() {
    }

}
