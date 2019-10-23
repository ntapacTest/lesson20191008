package com.maup.lesson20191008.controller;

import com.maup.lesson20191008.exceptions.UserNotFoundException;
import com.maup.lesson20191008.model.User;
import com.maup.lesson20191008.pojo.LoginRequest;
import com.maup.lesson20191008.pojo.SignUpRequest;
import com.maup.lesson20191008.security.jwt.JwtTokenProvider;
import com.maup.lesson20191008.service.EmailService;
import com.maup.lesson20191008.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(produces = "application/json")
public class LoginController {
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private EmailService emailService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.emailService=emailService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws UserNotFoundException {
        try {
            String email = loginRequest.getEmail();
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, loginRequest.getPassword()));
            User user = userService.findUserByEmail(email);//find by email

            if (user == null) {
                throw new UsernameNotFoundException("User with email: {} not found" + email);
            }

            String token = jwtTokenProvider.createToken(email, user.getRole());

            emailService.sendMail(user.getEmail(),"Login complete","Login {}"+user.getFirstName());

            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException | MessagingException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }


    @PostMapping("/auth/signup")
    public ResponseEntity createAccount(@RequestBody SignUpRequest signUpRequest) throws MessagingException {
        String firstName = signUpRequest.getFirstName();
        String lastName = signUpRequest.getLastName();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userService.save(user);

        emailService.sendMail(user.getEmail(),"Create account complete",String.format("New user %1$s created successfully",user.getFirstName()));

        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, user.getPassword()));
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        Map<Object, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
