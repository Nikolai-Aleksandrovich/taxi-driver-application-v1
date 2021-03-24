package com.compass.config;

import com.compass.service.BossDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Yuyuan Huang
 * @create 2021-03-20 16:47
 */
@Configuration
@EnableWebSecurity
public class BossWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BossDetailService bossDetailService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
                .userDetailsService(bossDetailService)
                .passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/findDriver","/myDriver")
                .access("hasRole(ROLE_USER)")
                .antMatchers("/","/**")
                .access("permitAll")
                .and()
                .formLogin()
                .loginPage("/bossLogin")
                .usernameParameter("user-name")
                .passwordParameter("pass-word")
                .defaultSuccessUrl("/bossHome")
                .and()
                .logout()
                .logoutSuccessUrl("/home");

    }

}
