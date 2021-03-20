package com.compass.controller;

import com.compass.repository.UserRepository;
import com.compass.security.UserRegistrationForm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Yuyuan Huang
 * @create 2021-03-19 17:08
 */
@Controller
@RequestMapping("/userRegister")
public class UserRegisterController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserRegisterController(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping
    public String processRegister(UserRegistrationForm userRegistrationForm){
        //表单提交用户信息到userRegistrationForm，在依靠下方的toUser，save持久化
        userRepository.save(userRegistrationForm.toUser(passwordEncoder));
        return "redirect:/userLogin";
    }

}
