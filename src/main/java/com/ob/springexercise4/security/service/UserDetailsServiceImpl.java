package com.ob.springexercise4.security.service;


//authentication of users from DB

// UserDetailsService interface provides a way for Spring Security
// to load user-specific data for authentication and authorization purposes.

import com.ob.springexercise4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//Authentication Manager uses the method loadUserByUsername to obtain user data from DB
//

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // look for the user in DB. Extracts the user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.ob.springexercise4.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword() , new ArrayList<>());

    }
}
