package com.compass.security;

import com.compass.domain.Driver;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Yuyuan Huang
 * @create 2021-03-19 23:25
 */
@Data
public class DriverRegistrationForm {

    private String driverName;
    private String passWord;
    private String driverLicense;
    private String carPlate;

    public Driver toDriver(PasswordEncoder passwordEncoder){
        return new Driver(driverName,passwordEncoder.encode(passWord),driverLicense,carPlate);
    }


}
