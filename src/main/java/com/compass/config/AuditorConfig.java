package com.compass.config;

import com.compass.domain.Boss;
import com.compass.domain.Driver;
import com.compass.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author Yuyuan Huang
 * @create 2021-04-06 14:24
 */
@Configuration
@EnableJpaAuditing
public class AuditorConfig {

    @Bean
    public SpringSecurityUserDetailsAuditorAware userDetailsAuditorAware(){
        return new SpringSecurityUserDetailsAuditorAware();
    }
    @Bean
    public SpringSecurityUserAuditorAware userAuditorProvider(){
        return new SpringSecurityUserAuditorAware();
    }
    @Bean
    public SpringSecurityBossAuditorAware bossAuditorProvider(){
        return new SpringSecurityBossAuditorAware();
    }
    @Bean
    public SpringSecurityDriverAuditorAware driverAuditorProvider(){
        return new SpringSecurityDriverAuditorAware();
    }

    public static class SpringSecurityUserDetailsAuditorAware implements AuditorAware<UserDetails> {

        @Override
        public Optional<UserDetails> getCurrentAuditor() {

            return Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getPrincipal)
                    .map(UserDetails.class::cast);
        }



    }


    public static class SpringSecurityUserAuditorAware implements AuditorAware<User> {

        @Override
        public Optional<User> getCurrentAuditor() {

            return Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getPrincipal)
                    .map(User.class::cast);
        }



    }
    public static class SpringSecurityDriverAuditorAware implements AuditorAware<Driver> {

        @Override
        public Optional<Driver> getCurrentAuditor() {

            return Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getPrincipal)
                    .map(Driver.class::cast);
        }



    }
    public static class SpringSecurityBossAuditorAware implements AuditorAware<Boss> {

        @Override
        public Optional<Boss> getCurrentAuditor() {

            return Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getPrincipal)
                    .map(Boss.class::cast);
        }



    }



}
