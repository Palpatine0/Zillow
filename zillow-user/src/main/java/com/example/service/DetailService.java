package com.example.service;

import com.example.dao.UserDao;
import com.example.dao.VerificationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class DetailService implements UserDetailsService {

    @Autowired
    private VerificationDao verificationDao;
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.entity.User user = userDao.getUserByUsername(username);
        String password = "";
        if (user == null) {
        } else {
            password = user.getPassword();
        }

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        User userDetail = new User(
                username,
                "{noop}" + password,
                true,
                true,
                true,
                true,
                authorities);

        return userDetail;

    }
}
