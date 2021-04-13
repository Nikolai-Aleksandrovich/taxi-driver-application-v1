package com.compass.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author Yuyuan Huang
 * @create 2021-03-18 9:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
@Entity
public class Car extends Auditable<Boss>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private final String plateNumber;
    @NotNull
    private final UUID bossId;
    @NotBlank
    private final String driverName;
    @NotNull
    public final CarParameter carParameter;
//    @NotBlank
//    private String createdBy;

    public enum CarParameter{
        LOGO,SEATTYPE,STRUCTURETYPE
    }

//    @NotBlank
//    private Date createdAt;
//    @PrePersist
//    void createdAt(){
//        this.createdAt = new Date();
//    }
//    private final LogoType carBrand;
//
//    private enum LogoType{
//        ORDINARY,MIDDLE_CLASS,LUXURY
////        TESLA,FORD,CADILLAC,JEEP,BUICK,LINCOLN,DODGE,CHEVROLET,CHRYSLER,GMC,TOYOTA,LEXUS,HONDA,ACURA,NISSAN,INFINITI,SUBARU,MAZDA,SUZUKI,MITSUBISHI
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
