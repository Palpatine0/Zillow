package com.example.service;

import com.example.dao.UserDao;
import com.example.dao.VerificationDao;
import com.example.vo.BaseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class AuthenticationService implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserDao userDao;
    @Autowired
    private VerificationDao verificationDao;


    // success handler
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Processing after successful authentication");
        BaseResult ok = BaseResult.ok();
        ok.setMsg("Authentication passed");

        // set respond, set it as JSON type as it write out
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(ok));
    }

    // failure handler
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("Processing after a failed authentication");
        BaseResult error = BaseResult.error();
        error.setMsg("Authentication failed");
        // set respond, set it as JSON type as it write out
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}
