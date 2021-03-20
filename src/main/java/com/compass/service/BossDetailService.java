package com.compass.service;

import com.compass.domain.Boss;
import com.compass.repository.BossRepository;
import com.compass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Yuyuan Huang
 * @create 2021-03-20 16:41
 */
public class BossDetailService implements UserDetailsService {
    private BossRepository bossRepository;
    @Autowired
    public BossDetailService(BossRepository bossRepository){
        this.bossRepository=bossRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Boss boss = bossRepository.findBossByBossName(s);
        if(boss!=null){
            return boss;
        }
        throw new UsernameNotFoundException("User'" + s + "'is Not Exist");
    }
}
