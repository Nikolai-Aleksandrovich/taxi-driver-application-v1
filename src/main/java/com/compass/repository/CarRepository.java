package com.compass.repository;

import com.compass.domain.CarDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Yuyuan Huang
 * @create 2021-03-18 10:58
 */
public interface CarRepository extends CrudRepository<CarDetails,Long> {

}
