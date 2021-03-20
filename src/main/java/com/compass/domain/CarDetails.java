package com.compass.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Yuyuan Huang
 * @create 2021-03-18 9:43
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
@Entity
public class CarDetails {
    @Id
    private final String id;
    private final String ownerName;


}
