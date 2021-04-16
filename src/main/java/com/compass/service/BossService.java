package com.compass.service;

import com.compass.domain.Driver;
import com.compass.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Yuyuan Huang
 * @create 2021-04-09 17:09
 */
@Service
public class BossService {

    private DriverRepository driverRepository;
    @Autowired
    public BossService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    @Transactional
    public Driver queryDriverService(UUID id,UUID bossID){
        Optional<Driver> optionalDriver = driverRepository.findDriverByIdAndBossId(id,bossID);
        return optionalDriver.orElse(null);
    }


}
