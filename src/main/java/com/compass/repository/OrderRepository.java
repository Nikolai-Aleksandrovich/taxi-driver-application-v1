package com.compass.repository;

import com.compass.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Yuyuan Huang
 * @create 2021-03-24 18:17
 */
public interface OrderRepository extends CrudRepository<Order,Long> {

}
