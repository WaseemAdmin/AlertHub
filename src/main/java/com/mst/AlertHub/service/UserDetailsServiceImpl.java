package com.mst.AlertHub.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mst.AlertHub.repository.UserRepository;

import com.mst.AlertHub.services.UserServiceDetails;

@Service
public class UserDetailsServiceImpl implements UserServiceDetails {


    @Autowired
    private UserRepository userRepository ;

    @Override
    public UserDetailsService userDetailsService() {

        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

                return userRepository.findUserByEmail(email);
            }
        };
    }

}
