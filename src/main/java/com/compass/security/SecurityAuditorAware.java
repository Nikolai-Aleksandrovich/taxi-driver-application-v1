package com.compass.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Yuyuan Huang
 * @create 2021-04-05 15:26
 */
@Component
public class SecurityAuditorAware implements AuditorAware<UUID> {


    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return Optional.empty();
        }

        return Optional.of((authentication.getPrincipal().getId));
    }
}
