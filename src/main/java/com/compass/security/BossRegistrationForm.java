package com.compass.security;

import com.compass.domain.Boss;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Yuyuan Huang
 * @create 2021-03-19 18:15
 */
@Data
public class BossRegistrationForm {
    private String bossName;
    private String passWord;
    private String bossLicense;

    private String carCounts;
//    private String carPlateNumber;

    private final String employeeCounts;
//    private final String employeeLicense;
    public Boss toBoss(PasswordEncoder passwordEncoder){
        return new Boss(bossName,passwordEncoder.encode(passWord),bossLicense,carCounts,employeeCounts);
    }

}
