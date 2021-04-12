package com.compass.controller;

import com.compass.domain.Boss;
import com.compass.domain.Car;
import com.compass.domain.Driver;
import com.compass.repository.*;
import com.compass.service.BossService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * 控制器编号：
 * 1、Get请求查询本账号管理的所有司机
 * 2、Get请求查询本账号管理的所有车辆
 * 3、Get请求，请求负责添加车辆的视图，在该视图上添加车辆信息并提交
 * 4、Get请求，查询数据库中月收入最高的三个司机
 * 5、Get请求，查询当前本Boss最好的司机和车辆
 * 6、Get请求，得到删除司机的视图
 * 7、Get请求，按照ID得到要修改的司机原信息
 * 8、Get请求，按照ID查询自己的司机
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
    private BossService bossService;


    @Autowired
    public BossController(BossService bossService, DriverPagingRepository driverPagingRepository, BossRepository bossRepository, DriverRepository driverRepository, DriverIncomeRepository driverIncomeRepository, CarRepository carRepository){
        this.bossRepository=bossRepository;
        this.bossService = bossService;
        this.carRepository = carRepository;
        this.driverRepository=driverRepository;
        this.driverIncomeRepository = driverIncomeRepository;
        this.driverPagingRepository = driverPagingRepository;
    }

    @GetMapping(value = {"/myDriver","/selectMyDriver"})
    //1、Get请求查询本账号管理的所有司机
    //如果依照Id查找自己的司机，没有输入id直接查找，那就返回所有司机
    public String allDriverForm(@AuthenticationPrincipal Boss boss,Model model){
        //查询所有司机
        List<List<Object>> list = driverRepository.findIdAndDriverNameByBossId(boss.getBossId());
        model.addAllAttributes(list);
        return "myDriver";
    }

    @GetMapping("/allMyCar")
    //2、Get请求查询本账号管理的所有车辆
    public String allCar(@AuthenticationPrincipal Boss boss,Model model){
        List<Car> carList = new ArrayList<>(carRepository.findAllByBossId(boss.getBossId()));
        model.addAllAttributes(carList);
        return "allMyCar";

    }


    @GetMapping("/allMyCar/addCar")
    //3、Get请求，请求负责添加车辆的视图，在该视图上添加车辆信息并提交
    public String carForm(){

        return "inputCarDetails";
    }

    @GetMapping("/Top3Employee")
    //4、Get请求，查询数据库中月收入最高的三个司机
    public String topEmployeeForm(Model model){
        //查询全服最好的三个司机
        Page<Driver> drivers  = driverPagingRepository.findAll(PageRequest.of(0,3,Sort.by("income").descending()));
        model.addAttribute(drivers.getContent());
        return "top3Employee";
    }

    @GetMapping("/myBestEmployee")
    //5、Get请求，查询当前本Boss最好的司机和车辆
    public String bestEmployee(@AuthenticationPrincipal Boss boss,Model model){
        //查询我最好的司机
        List<Long> idList;
        idList = driverIncomeRepository.findIdByBossIdOrderByIncome(boss.getBossId());
        Optional<Driver> driver = driverRepository.findById(idList.get(0));
        if(driver.isPresent()){
            model.addAttribute("bestDriver",driver.get());
            model.addAttribute("bestCar",carRepository.findByPlateNumber(driver.get().getCarPlate()));
            return "bestEmployee";
        }else {
            model.addAttribute("bestDriver",null);
            model.addAttribute("bestCar",null);
            return "bestEmployeeNotFound";
        }

    }
    @GetMapping("allMyDriver/fireDriver")
    //6、Get请求，得到删除司机的视图
    public String fireDriver(){
        return "allMyDriver/fireDriver/inputId";
    }

    @GetMapping("allMyDriver/changeDriverInfo/{id}")
    //7、Get请求，按照ID得到要修改的司机原信息
    public String changeDriverInfo(@PathVariable("id")UUID id,@AuthenticationPrincipal Boss boss,Model model){
        model.addAttribute("changeDriver",bossService.queryDriverService(id,boss.getBossId()));
        return "allMyDriver/changeDriverInfo";
    }

    @GetMapping("/selectMyDriver/{id}")
    //8、Get请求，按照ID查询自己的司机
    public String driverForm(@PathVariable(name = "id") UUID id,@AuthenticationPrincipal Boss boss,Model model){
        //查询我的某个司机（利用id）
        model.addAttribute("selectedDriver",bossService.queryDriverService(id,boss.getBossId()));
        return "allMyDriver/selectedDriver";
    }


    @GetMapping("/allMyCar/selectCar/{id}")
    public String selectCar(@PathVariable("id")Long id,Model model,@AuthenticationPrincipal Boss boss){
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()){
            if(optionalCar.get().getBossId()==boss.getBossId()){
                model.addAttribute("selectedCar",optionalCar.get());
                return "allMyCar/selectedCar";
            }else {
                return "redirect:/accessNotEnough";
            }
        }throw new UsernameNotFoundException("Car ' "+id+" 'is Not Existed");
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
    public String changeDriver(@PathVariable("id")Long id,@ModelAttribute("changeDriver")Driver driver){
        driverRepository.save(driver);
        return "redirect:/allMyDriver";

    }

    @PostMapping("/allMyCar/addCar")
    public String processCar(@Valid Car car,@AuthenticationPrincipal Boss boss){
        carRepository.save(car);
        return "redirect:/allMyCar";
    }

    @PostMapping("/allMyCar/deleteCar/{id}")
    //根据Id删除数据库中的车辆
    public String deleteCar(@PathVariable("id")Long id,@AuthenticationPrincipal Boss boss){
        carRepository.deleteById(id);
        return "redirect:/allMyCar";
    }

}
