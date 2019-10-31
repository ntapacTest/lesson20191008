package com.maup.lesson20191008.security.jwt;

import com.maup.lesson20191008.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole()),
                true,
                user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String role) {
        List<String> roles= new ArrayList<>(Arrays.asList(role));
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
