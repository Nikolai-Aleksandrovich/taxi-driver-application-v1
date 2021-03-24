package com.compass.config;

import com.compass.service.UserDetailService;
import com.compass.service.UserDetailService;
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
 * @create 2021-03-20 13:51
 */
@Configuration
@EnableWebSecurity
public class UserWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailsService;
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                //调用service，将userDetailsService传过去，将编码器传过去
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .antMatchers("orderTaxi")//"/findMeCustomer","findMeBestDriver",
                .hasRole("ROLE_USER")//找客人，找车，找司机需要认证
                .antMatchers("/","/**")
                .permitAll()//除了以上几点，其余不需要认证
        .and()
                .formLogin()
                .loginPage("/userLogin")//判定没有登陆，那就去登陆页面
//                .loginProcessingUrl("/authenticate")
                .usernameParameter("user-name")
                .passwordParameter("pass-word")
                .defaultSuccessUrl("/home")
        .and()
                .logout()
                .logoutSuccessUrl("/");



//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/findMeCustomer","findMeBestDriver","orderTaxi")
//                .access("hasRole('ROLE_USER')")
                //.antMatchers("/50%OFF")
        //        .access("hasRole('ROLE_USER')&&"+"T(java.util.Calendar).getInstance().get("+"T(java.util.Calendar)DAY_OF)WEEK=="+"T(java.util.Calendar).TUESDAY")
//                .antMatchers("/","/**")
//                .access("permitAll");
    }

}
