package com.compass.controller;

import com.compass.domain.Boss;
import com.compass.domain.Car;
import com.compass.domain.Driver;
import com.compass.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @author Yuyuan Huang
 * @create 2021-03-29 17:36
 */
@Controller
@Slf4j
@RequestMapping("/boss")
@SessionAttributes(names = {"car","changeDriver"})

public class BossController {
    @ModelAttribute(name = "car")
    public Car car(){
        return new Car();
    }
    @ModelAttribute(name = "changeDriver")
    public Driver driver(){
        return new Driver();
    }

    private BossRepository bossRepository;
    private DriverIncomeRepository driverIncomeRepository;
    private DriverRepository driverRepository;
    private CarRepository carRepository;
    private DriverPagingRepository driverPagingRepository;


    @Autowired
    public BossController(DriverPagingRepository driverPagingRepository,BossRepository bossRepository,DriverRepository driverRepository,DriverIncomeRepository driverIncomeRepository,CarRepository carRepository){
        this.bossRepository=bossRepository;
        this.carRepository = carRepository;
        this.driverRepository=driverRepository;
        this.driverIncomeRepository = driverIncomeRepository;
        this.driverPagingRepository = driverPagingRepository;
    }

    @GetMapping(value = {"/myDriver","/selectMyDriver"})
    //如果依照Id查找自己的司机，没有输入id直接查找，那就返回所有司机
    public String allDriverForm(@AuthenticationPrincipal Boss boss,Model model){
        //查询所有司机
        List<List<Object>> list = driverRepository.findIdAndDriverNameByBossId(boss.getBossId());
        model.addAllAttributes(list);
        return "myDriver";
    }

    @GetMapping("/allMyCar")
    public String allCar(@AuthenticationPrincipal Boss boss,Model model){
        List<Car> carList = new ArrayList<>(carRepository.findAllByBossId(boss.getBossId()));
        model.addAllAttributes(carList);
        return "allMyCar";

    }


    @GetMapping("/addCar")
    public String carForm(@AuthenticationPrincipal Boss boss,@ModelAttribute("car") Car car){
        if(car.getCreatedBy()==null){
            car.setCreatedBy(boss.getBossName());
        }
        return "inputCarDetails";
    }

    @GetMapping("/Top3Employee")
    public String topEmployeeForm(Model model){
        //查询全服最好的三个司机
        Page<Driver> drivers  = driverPagingRepository.findAll(PageRequest.of(0,3,Sort.by("income").descending()));
        model.addAttribute(drivers.getContent());
        return "top3Employee";
    }

    @GetMapping("/myBestEmployee")
    public String bestEmployee(@AuthenticationPrincipal Boss boss,Model model){
        //查询我最好的司机
        List<Long> idList;
        idList = driverIncomeRepository.findIdByBossIdOrderByIncome(boss.getBossId());
        Optional<Driver> driver = driverRepository.findById(idList.get(0));
        if(driver.isPresent()){
            model.addAttribute("name",driver.get().getDriverName());
            model.addAttribute("id",driver.get().getId());
            model.addAttribute("carPlate",driver.get().getCarPlate());
            model.addAttribute("carId",carRepository.findIdByPlateNumber(driver.get().getCarPlate()));
        }else {
            model.addAttribute("name","you don't hire any driver yet");
            model.addAttribute("id","null");
            model.addAttribute("carPlate","null");
            model.addAttribute("carId","null");
        }
        return "bestDriver";
    }

    @GetMapping("allMyDriver/changeDriverInfo/{id}")
    public String changeDriverInfo(@PathVariable("id")Long id,@AuthenticationPrincipal Boss boss,Model model){
        Optional<Driver> optionalDriver = driverRepository.findDriverByIdAndBossId(id,boss.getBossId());
        if (optionalDriver.isPresent()){
            model.addAttribute("changeDriver",optionalDriver.get());
            return "setNewDriverInfo";
        }
            throw new UsernameNotFoundException("Id '" + id + "'is not exist");

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
        return "redirect:/myDriver";
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
        return "redirect:/myDriver";

    }


    @PostMapping("allMyDriver/changeDriverInfo/{id}")
    public String changeDriver(@PathVariable("id")Long id,@AuthenticationPrincipal Boss boss,@ModelAttribute("changeDriver")Driver driver,Model model){
        driverRepository.

    }

    @PostMapping("/allMyCar/addCar")
    public Car processCar(@Valid Car car,@AuthenticationPrincipal Boss boss){
        carRepository.save(car);
        Optional<Car> tempCar = carRepository.findById(car.getId());
        if (tempCar.isPresent()){
            carRepository.updateCreatedByById(boss.getBossName(),car.getId());
        }else {throw new  UsernameNotFoundException("Car is Not Exist");}

        return carRepository.findById(car.getId()).get();

    }

    @PostMapping("/allMyCar/deleteCar")
    public

}
