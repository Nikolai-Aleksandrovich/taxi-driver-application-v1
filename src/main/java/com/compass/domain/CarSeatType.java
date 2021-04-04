package com.compass.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Yuyuan Huang
 * @create 2021-03-24 21:39
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true )
@RequiredArgsConstructor
@Entity
public class CarSeatType {
    @Id
    private final String id;

    private final String name;


}
