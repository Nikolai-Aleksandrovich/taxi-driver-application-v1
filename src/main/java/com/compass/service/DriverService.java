package com.compass.service;

import com.compass.repository.DriverRepository;
import org.springframework.stereotype.Service;

/**
 * @author Yuyuan Huang
 * @create 2021-03-30 14:59
 */
@Service
public class DriverService {
    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }


}
