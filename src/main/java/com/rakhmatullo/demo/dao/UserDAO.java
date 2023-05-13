package com.rakhmatullo.demo.dao;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDAO {

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(new User("bouli@gmail.com", "password", Collections.singleton((GrantedAuthority) () -> "ROLE_ADMIN")), new User("user@gmail.com", "password", Collections.singleton((GrantedAuthority) () -> "ROLE_USER")));

    public UserDetails findByUserEmail(String email) {
        return APPLICATION_USERS
                .stream()
                .filter(u -> u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        ;
    }
}
