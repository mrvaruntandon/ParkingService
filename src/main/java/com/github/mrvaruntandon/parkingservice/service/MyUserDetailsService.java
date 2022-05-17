package com.github.mrvaruntandon.parkingservice.service;

import com.github.mrvaruntandon.parkingservice.entity.User;
import com.github.mrvaruntandon.parkingservice.model.MyUserDetails;
import com.github.mrvaruntandon.parkingservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("username or password is incorrect"));
        return new MyUserDetails(user);
    }
}
