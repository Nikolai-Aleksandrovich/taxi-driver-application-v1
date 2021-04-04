package com.compass.controller;

import com.compass.domain.Car;
import com.compass.domain.Destination;
import com.compass.domain.Order;
import com.compass.repository.CarRepository;
import com.compass.repository.DestinationRepository;
import com.compass.repository.PoiRepository;
import com.compass.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Yuyuan Huang
 * @create 2021-03-24 18:13
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes(names = "order")//名为order的模型可以保存在session中，跨请求使用
public class ChooseDestinationController {

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "destination")
    public Destination destination(){
        return new Destination();
    }

    private DestinationRepository destinationRepository;
    private CarRepository carRepository;
    private UserRepository userRepository;

    @Autowired
    public ChooseDestinationController(CarRepository carRepository,DestinationRepository destinationRepository,UserRepository userRepository){
        this.destinationRepository = destinationRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String showDesign(Model model){
        List<Car> carRequirement = new ArrayList<>();
        carRepository.findAll().forEach(carRequirement::add);//carRequirement加入数据库中所有车数据

        Car.CarParameter[] parameters = Car.CarParameter.values();//parameter加入“seatType，carStructure，logo”
        for(Car.CarParameter parameter:parameters){
            model.addAttribute(parameter.toString().toLowerCase(),filterByParameter(carRequirement,parameter));//把数据库中筛选过的参数传给model，通过它传给视图
        }
        return "/destDesign";
    }
    @PostMapping
    public String processDestDesign(@Valid Destination design, Errors errors,@ModelAttribute Order order){
        if(errors.hasErrors()){//先判断是否存在输入错误
            return "/design";
        }
        Destination saved = destinationRepository.save(design);//若无错误，储存设计好的信息到数据库
        order.addDesign(design);
        return "redirect:/orders/current";
    }

    /**
     *
     * @param cars  元素为Car类型的列表，数据来自数据库
     * @param parameter 筛选cars的条件，
     * @return 经过筛选的Parameter  [猪肉，羊肉，白菜，萝卜]-(肉)->[猪肉，羊肉]
     */
    private List<Car> filterByParameter(List<Car> cars, Car.CarParameter parameter){
        return cars.stream().filter(x ->x.getCarParameter().equals(parameter)).collect(Collectors.toList());
    }

}
