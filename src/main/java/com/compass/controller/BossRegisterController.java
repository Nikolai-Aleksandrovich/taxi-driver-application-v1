package com.compass.controller;

import com.compass.repository.BossRepository;
import com.compass.security.BossRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yuyuan Huang
 * @create 2021-03-19 17:58
 */
@Controller
@RequestMapping("/bossRegister")
public class BossRegisterController {
    private PasswordEncoder passwordEncoder;
    private BossRepository bossRepository;

    @Autowired
    public BossRegisterController(PasswordEncoder passwordEncoder,BossRepository bossRepository){
        this.bossRepository = bossRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("register")
    public String register(){
        return "/bossregister";
    }

    @PostMapping
    public String processBoss(BossRegistrationForm bossRegistrationForm){
        bossRepository.save(bossRegistrationForm.toBoss(passwordEncoder));
        return "redirect:/bossLogin";
    }


}
