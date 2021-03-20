package com.compass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yuyuan Huang
 * @create 2021-03-17 20:53
 */
@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
