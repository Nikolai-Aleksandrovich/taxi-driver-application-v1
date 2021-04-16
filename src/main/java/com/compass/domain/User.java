package com.compass.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.*;
import java.util.*;

/**
 * @author Yuyuan Huang
 * @create 2021-03-18 11:15
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
@RequiredArgsConstructor
@Entity
public class User extends Auditable<UserDetails> implements UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //依赖数据库自动生成Id值
    private UUID id;
    private final String userName;
    private final String passWord;
    private final String fullName;
//    private HashMap<String,String> home = new HashMap<>();
    private final String street;
    private final String district;
    private final String city;
    private final String province;
    private final String phoneNumber;
    private final String latitude;
    private final String longitude;
//
//    public User(String userName, String encode, String fullName, String street,String district, String city, String province, String phoneNumber, String latitude, String longitude) {
//        this.userName = userName;
//        this.passWord = encode;
//        this.fullName = fullName;
//        this.street = street;
//        this.district = district;
//        this.city = city;
//        this.province = province;
//        this.phoneNumber = phoneNumber;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        //给所有用户赋予ROLE_USER
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
