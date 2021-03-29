package com.compass.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Yuyuan Huang
 * @create 2021-03-18 9:43
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String plateNumber;
    private final String ownerName;
    private final String driverName;
    public final CarParameter carParameter;

    public enum CarParameter{
        LOGO,SEATTYPE,STRUCTURETYPE
    }
//    private final LogoType carBrand;
//
//    private enum LogoType{
//        ORDINARY,MIDDLE_CLASS,LUXURY
////        TESLA,FORD,CADILLAC,JEEP,BUICK,LINCOLN,DODGE,CHEVROLET,CHRYSLER,GMC,TOYOTA,LEXUS,HONDA,ACURA,NISSAN,INFINITI,SUBARU,MAZDA,SUZUKI,MITSUBISHI
//    }
//
//
//    private final StructureType carType;
//
//    private enum StructureType{
//        ORDINARY,SPORT,LUXURY
////        SEDAN,HATCHBACK,SUV,MPV,COUPE,ROADSTER,CABRIO, CROSSOVER,PICKUP,WAGON
//    }
//private final SeatType seatsCount;
//
//    private enum SeatType{
//        ORDINARY,MIDDLE,LARGE
//    }





}
