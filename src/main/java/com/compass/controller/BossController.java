package com.compass.controller;

import com.compass.domain.Boss;
import com.compass.domain.Car;
import com.compass.domain.Driver;
import com.compass.repository.BossRepository;
import com.compass.repository.CarRepository;
import com.compass.repository.DriverIncomeRepository;
import com.compass.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author Yuyuan Huang
 * @create 2021-03-29 17:36
 */
@Controller
@Slf4j
@RequestMapping("/boss")

public class BossController {
    @ModelAttribute(value = "car")
    public Car car(){
        return new Car();
    }
    public Driver driver(){
        return new Driver();
    }

    private BossRepository bossRepository;
    private DriverIncomeRepository driverIncomeRepository;
    private DriverRepository driverRepository;
    private CarRepository carRepository;


    @Autowired
    public BossController(BossRepository bossRepository,DriverRepository driverRepository,DriverIncomeRepository driverIncomeRepository,CarRepository carRepository){
        this.bossRepository=bossRepository;
        this.carRepository = carRepository;
        this.driverRepository=driverRepository;
        this.driverIncomeRepository = driverIncomeRepository;
    }

    @GetMapping(value = {"/myDriver","/selectMyDriver"})
    //如果依照Id查找自己的司机，没有输入id直接查找，那就返回所有司机
    public String allDriverForm(@AuthenticationPrincipal Boss boss,Model model){
        //查询所有司机
        List<List<Object>> list = driverRepository.findIdAndDriverNameByBossId(boss.getBossId());
        model.addAllAttributes(list);
        return "myDriver";
    }


    @GetMapping("/Top3Employee")
    public String topEmployeeForm(Model model){
        //查询全服最好的三个司机
        PageRequest pageRequest = PageRequest.of(0,3,Sort.by("income").descending());
        List<List<Object>> list = new ArrayList<>();
        list = driverRepository.findIdAndNameOrderByIncomeAtDesc();
        model.addAllAttributes(list);
        return "top3Employee";
    }

    @GetMapping("/myBestEmployee")
    public String bestEmployee(@AuthenticationPrincipal Boss boss,Model model){
        //查询我最好的司机
        List<Long> idList = new ArrayList<>();
        idList = driverIncomeRepository.findIdByBossIdOrderByIncome(boss.getBossId());
        Optional<Driver> driver = driverRepository.findById(idList.get(0));
        if(driver.isPresent()){
            model.addAttribute("name",driver.get().getDriverName());
            model.addAttribute("id",driver.get().getId());
            model.addAttribute("carPlate",driver.get().getCarPlate());
        }else {
            model.addAttribute("name","you don't hire any driver yet");
            model.addAttribute("id","null");
            model.addAttribute("carPlate","null");
        }
        return "bestDriver";
    }



    @GetMapping("/selectMyDriver/{id}")
    public String driverForm(@PathVariable(name = "id") Long id,@AuthenticationPrincipal Boss boss,Model model){
        //查询我的某个司机（利用id）
        Optional<Driver> optionalDriver = driverRepository.findByBossId(boss.getBossId());
        if(optionalDriver.isPresent()){
            model.addAttribute("id",optionalDriver.get().getId());
            model.addAttribute("bossId",optionalDriver.get().getBossId());
            model.addAttribute("fullName",optionalDriver.get().getDriverName());
            model.addAttribute("License",optionalDriver.get().getDriverLicense());
            model.addAttribute("carPlate",optionalDriver.get().getCarPlate());
            return "driverInformation";
        }throw new UsernameNotFoundException("Driver'" + id +"'Not Found");
    }

    @PostMapping("/fireDriver/{id}")
    public String fireDriver(@PathVariable("id")Long id){
        driverRepository.deleteById(id);
        return "myDriver";
    }


    @PostMapping("/hireDriver")
    public String processDriver(@PathVariable("id")Long id,@AuthenticationPrincipal Boss boss){
        //要雇佣司机，通过当前司机id查询，必须当前司机有自己的账号，并且，当前司机没有雇主
        Optional<Driver> optDriver = driverRepository.findById(id);
        if(optDriver.isPresent()){
            if(optDriver.get().getAccountStatus().equals("0")){
                driverRepository.updateAccountStatusBossIdById(id,1,boss.getBossId());
            }else {
                return "alreadyHasBoss";
            }
        }else {throw new UsernameNotFoundException(
                "Driver'" + id + "'is Not Exist"
        );}
        return "myDriver";

    }

    @PostMapping("/addCar")
    public Car processCar(@Valid Car car,@AuthenticationPrincipal Boss boss){
        carRepository.save(car);
        Optional<Car> tempCar = carRepository.findById(car.getId());
        if (tempCar.isPresent()){
            tempCar.get().
        }

    }
}
