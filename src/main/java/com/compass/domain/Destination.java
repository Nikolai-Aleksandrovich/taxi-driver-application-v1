package com.compass.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author Yuyuan Huang
 * @create 2021-03-22 15:10
 */
@Data
//@RequiredArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
//@Table(name = "Dest")
@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;


    @NotBlank(message = "you have to name your trip")
    private String tripName;
//    private String poiName;

    @ManyToMany
    @Size(min = 1, message ="You must choose at least one car")
    private List<Car> car;

//    @ManyToMany(targetEntity = Poi.class)
//    @Size(min=1,message = "You must choose at least one Poi")
//    private List<Poi> poiList;

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
