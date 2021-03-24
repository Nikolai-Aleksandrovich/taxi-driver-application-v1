package com.compass.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yuyuan Huang
 * @create 2021-03-20 13:48
 */
@Controller
@RequestMapping("/driverRegister")
public class DriverRegisterController {
    private PasswordEncoder passwordEncoder;

}
