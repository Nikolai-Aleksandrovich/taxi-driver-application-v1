package com.compass.service;

import com.compass.domain.User;
import com.compass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Yuyuan Huang
 * @create 2021-03-20 13:55
 */
@Service
public class UserDetailService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public UserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(s);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("User '" + s + "'Not Found");
    }
}
