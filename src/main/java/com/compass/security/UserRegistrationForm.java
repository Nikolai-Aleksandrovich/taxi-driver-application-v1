package com.compass.security;

import com.compass.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Yuyuan Huang
 * @create 2021-03-19 17:19
 * @function: transform input into User.class
 */
@Data
public class UserRegistrationForm {
    private String userName;
    private String passWord;
    private String fullName;
    private String street;
    private String district;
    private String city;
    private String province;
    private String phoneNumber;
    private String latitude;
    private String longitude;

    public User toUser(PasswordEncoder passwordEncoder){
        //转换输入到User类型
        return new User(userName,passwordEncoder.encode(passWord),fullName,street,district,city,province,phoneNumber,latitude,longitude);
    }

}
