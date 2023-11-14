package com.example.service;

import com.example.dao.VerificationDao;
import com.example.entity.VerificationCode;
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

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        VerificationCode verificationCode = verificationDao.getVerificationCode(phone);
        String vCode = "";
        if (verificationCode == null) {

        } else {
            vCode = verificationCode.getVerificationCode();
        }

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        User userDetail = new User(
                phone,
                "{noop}" + vCode,
                true,
                true,
                true,
                true,
                authorities);

        return userDetail;

    }
}
