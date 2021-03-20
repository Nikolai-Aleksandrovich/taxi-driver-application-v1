package com.compass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yuyuan Huang
 * @create 2021-03-18 10:42
 */
@Controller
@RequestMapping("/registerCar")
public class CarController {
    @GetMapping
    public String registerView(){
        return "carregister";
    }
    @PostMapping
    public String processCarRegistration(){
        return "redirect:/driverLogin";
    }


}
