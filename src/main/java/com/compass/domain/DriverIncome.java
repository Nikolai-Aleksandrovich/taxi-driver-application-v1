package com.compass.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Yuyuan Huang
 * @create 2021-03-29 17:34
 */
@Data
@Entity
public class DriverIncome {
    @Id
    private Long id;
    private Long BossId;
    private String fullName;
    private String income;
}
