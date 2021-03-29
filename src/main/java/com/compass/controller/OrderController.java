package com.compass.controller;

import com.compass.domain.Order;
import com.compass.domain.User;
import com.compass.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * @author Yuyuan Huang
 * @create 2021-03-28 15:26
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private OrderRepository orderRepository;
    public OrderController(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }


    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order){

        if(order.getName()==null){
            order.setName(user.getFullName());
        }
        if(order.getStartProvince()==null){
            order.setStartProvince(user.getProvince());
        }
        if(order.getDestProvince()==null){
            order.setDestProvince(user.getProvince());
        }
        if(order.getDestCity()==null){
            order.setDestCity(user.getCity());
        }
        if(order.getStartCity()==null){
            order.setStartCity(user.getCity());
        }
        if(order.getStartLatitude()==null){
            order.setStartLatitude(user.getLatitude());
        }
        if(order.getStartLongitude()==null){
            order.setStartLongitude(user.getLongitude());
        }
        if(order.getDestLatitude()==null){
            order.setDestLatitude(user.getLatitude());
        }
        if(order.getDestLongitude()==null){
            order.setDestLongitude(user.getLongitude());
        }
        if(order.getNumber()==null){
            order.setNumber(user.getPhoneNumber());
        }
        if(order.getStartRoad()==null){
            order.setStartRoad(user.getStreet());
        }
        if(order.getDestRoad()==null){
            order.setDestRoad(user.getStreet());
        }
        if(order.getDestDistrict()==null){
            order.setDestDistrict(user.getDistrict());
        }
        if(order.getStartDistrict()==null){
            order.setStartDistrict(user.getDistrict());
        }
        return "orderForm";
    }

    @GetMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        if(errors.hasErrors()){
            return "orderForm";
        }
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/success";
    }


}
