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

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//                .loginProcessingUrl("/login")
                .loginProcessingUrl("/login")
//                .successForwardUrl("/detail/getDetail")
                .successForwardUrl("/details")
                .successHandler(authenticationService)
                .failureHandler(authenticationService)
                .and().rememberMe()
                .tokenValiditySeconds(12096000)
                // not intercepting routes
//                .and().authorizeRequests().antMatchers("/login/sendVerificationCode").permitAll()
                .and().authorizeRequests().antMatchers("/sendyzm").permitAll()
                .anyRequest().authenticated();
        super.configure(http);
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/login").permitAll() // Allow access to /login without authentication
                .antMatchers("/user/sendVerificationCode").permitAll() // Allow access to /sendVerificationCode without authentication
                .antMatchers("/user/register").permitAll() // Allow access to /register without authentication
                .antMatchers("/user/getUser").permitAll() // Allow access to /getUser without authentication
                .anyRequest().authenticated() // All other requests require authentication
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

