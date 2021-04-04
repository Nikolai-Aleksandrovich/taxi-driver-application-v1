package com.compass.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yuyuan Huang
 * @create 2021-03-22 14:57
 */
@Data
@RequiredArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @ManyToOne
    private User user;
    @NotBlank(message = "please input your name")
    private String name;
    @NotBlank(message = "please input your phone number")
    private String number;

    @NotBlank(message = "destination's longitude is required!")
    private String destLongitude;
    @NotBlank(message = "destination's latitude is required")
    private String destLatitude;

    @NotBlank(message = "destination province is required!")
    private String destProvince;
    @NotBlank(message = "destination city is required!")
    private String destCity;
    @NotBlank(message = "destination district is required")
    private String destDistrict;
    @NotBlank(message = "destination road is required")
    private String destRoad;
    @NotBlank(message = "destination location's name is required")
    private String destLocationName;

    @NotBlank(message = "start longitude is required!")
    private String startLongitude;
    @NotBlank(message = "start latitude is required")
    private String startLatitude;

    @NotBlank(message = "start time is required")
    private Date startTime;

    @NotBlank(message = "start province is required!")
    private String startProvince;
    @NotBlank(message = "start city is required!")
    private String startCity;
    @NotBlank(message = "start district is required")
    private String startDistrict;
    @NotBlank(message = "start road is required")

    private String startRoad;
    @NotBlank(message = "start location's name is required")
    private String startLocationName;

    @NotBlank(message = "please input number of passengers")
    @Size(max = 4,message = "max passenger numbers are 4, you can order another car for other passengers")
    @Size(min = 1,message = "at lease 1 passenger")
    private String  passengerNumbers;

    @ManyToMany(targetEntity = Destination.class)
    private List<Destination> destinations = new ArrayList<>();


    public void addDesign(Destination destination){
        this.destinations.add(destination);
    }

    @PrePersist
    void placedAt(){
        this.placedAt = new Date();
    }
}
