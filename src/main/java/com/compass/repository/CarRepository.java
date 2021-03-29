package com.compass.repository;

import com.compass.domain.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Yuyuan Huang
 * @create 2021-03-18 10:58
 */
public interface CarRepository extends CrudRepository<Car,Long> {

}
