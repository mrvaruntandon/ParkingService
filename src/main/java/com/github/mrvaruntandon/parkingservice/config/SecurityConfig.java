package com.github.mrvaruntandon.parkingservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    //authentication -- confirm the identity of the users
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("adminpassword")
                .roles("ADMIN");
        */

        //jdbc authentication, using the default schema = users and authorities tables
        //auth.jdbcAuthentication().dataSource(dataSource);

        //authentication using UserDetailsService
        auth.userDetailsService(userDetailsService);
    }

    /**
     * If we don't define the PasswordEncoder Bean then we get the following error
     * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //matchers are ordered from most restricted to the least restricted
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }
}
