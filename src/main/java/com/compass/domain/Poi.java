package com.compass.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Type;

/**
 * @author Yuyuan Huang
 * @create 2021-03-20 19:04
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
@Entity
public class Poi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String idCode;
    private final String name;
    private final String type;
    private final String useType;
    private final PopularLevel checkInCount;
    private final String userCount;
    private final String latitude;
    private final String longitude;

    private enum PopularLevel{
        HOTTEST,HOT,RELATIVELYHOT,ORDINARY,COUNTRYSIDE,NOWHERE
    }



}
