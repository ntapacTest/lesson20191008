package com.maup.lesson20191008.cofig;

import com.maup.lesson20191008.model.User;
import com.maup.lesson20191008.repo.UserRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**").authorizeRequests()
                .mvcMatchers("/", "login**", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepo userRepository) {
        return map -> {
            String id = String.valueOf(map.get("sub"));
            User user = userRepository.findById(id).orElseGet(() -> {
                User newUser = new User();
                newUser.setId(id);
                newUser.setFirstName((String) map.get("given_name"));
                newUser.setLastName((String) map.get("family_name"));
                newUser.setEmail((String) map.get("email"));
//                newUser.setGender((String) map.get("gender"));
                newUser.setLocale((String) map.get("locale"));
//                newUser.setUserpic((String) map.get("picture"));
                return newUser;
            });
//            user.setLastVisit(LocalDateTime.now());
            return userRepository.save(user);
        };
    }
}