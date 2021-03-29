package com.compass.controller;

import com.compass.domain.Boss;
import com.compass.domain.Car;
import com.compass.domain.Driver;
import com.compass.repository.BossRepository;
import com.compass.repository.DriverIncomeRepository;
import com.compass.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Yuyuan Huang
 * @create 2021-03-29 17:36
 */
@Controller
@Slf4j
@RequestMapping("/boss")
@SessionAttributes("driver")
public class BossController {
    @ModelAttribute("driver")
    public Driver driver(){
        return new Driver();
    }

    private BossRepository bossRepository;
    private DriverIncomeRepository driverIncomeRepository;
    private DriverRepository driverRepository;


    @Autowired
    public BossController(BossRepository bossRepository,DriverRepository driverRepository,DriverIncomeRepository driverIncomeRepository){
        this.bossRepository=bossRepository;
        this.driverRepository=driverRepository;
        this.driverIncomeRepository = driverIncomeRepository;
    }

    @GetMapping("/Top3Employee")
    public String topEmployeeForm(Model model){
            PageRequest pageRequest = PageRequest.of(0,3,Sort.by("income").descending());
            driverRepository.findAll(pageRequest)

        return "top3Employee";
    }
    @GetMapping("/myBestEmployee")
    public String bestEmployee(@AuthenticationPrincipal Boss boss,Model model){
        List<Long> idList = new ArrayList<>();
        idList = driverIncomeRepository.findIdByBossIdOrderByIncome(boss.getBossId());
        Optional<Driver> driver = driverRepository.findById(idList.get(0));
        if(driver.isPresent()){
            model.addAttribute(driver.get().getDriverName());
        }else {
            model.addAttribute("you don't hire any driver yet");
        }
        return "bestDriver";
    }
    @GetMapping("/selectDriver")
    public String driverForm(@ModelAttribute Driver driver,){


    }
    @PostMapping("/hireDriver")
    public String processDriver(@Valid Driver driver){
        //要雇佣司机，通过当前司机id查询，必须当前司机有自己的账号，并且，当前司机没有雇主
        driverRepository.findById()
    }

    @PostMapping("/addCar")
    public String processCar(@Valid Car car,){

    }
}
