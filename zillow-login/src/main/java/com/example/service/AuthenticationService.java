package com.example.service;

import com.example.vo.ZillowResult;
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


    // success handler
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Processing after successful authentication");
        ZillowResult ok = ZillowResult.ok();
        ok.setMsg("Successfully logged in");

        // set respond, set it as JSON type as it write out
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(ok));
    }

    // failure handler
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("Processing after a failed authentication");
        ZillowResult error = ZillowResult.error();

        // set respond, set it as JSON type as it write out
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}
