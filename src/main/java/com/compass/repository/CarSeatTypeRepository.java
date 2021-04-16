package com.compass.repository;

import com.compass.domain.CarSeatType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yuyuan Huang
 * @create 2021-03-24 21:48
 */
@Repository
public interface CarSeatTypeRepository extends CrudRepository<CarSeatType,Long> {

}
