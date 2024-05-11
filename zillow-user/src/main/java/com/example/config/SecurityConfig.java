package com.example.config;

import com.example.service.AuthenticationService;
import com.example.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private DetailService detailService;

    // auth manager
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // custom login logic
        auth.userDetailsService(detailService);
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Allow access to /login without authentication
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/deleteUser").permitAll()
                .antMatchers("/user/sendVerificationCode").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/getUsers").permitAll()
                .antMatchers("/user/getUserByUsername").permitAll()
                .antMatchers("/user/getUserById").permitAll()
                // All other requests require authentication
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/user/login")
                .successForwardUrl("/details")
                .successHandler(authenticationService)
                .failureHandler(authenticationService)
                .and()
                .rememberMe()
                .tokenValiditySeconds(12096000);
        // csrf kill
        http.csrf().disable();
        // cors configuration
        http.cors().configurationSource(corsConfigurationSource());
    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}

