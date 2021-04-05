package com.compass.repository;

import com.compass.domain.Driver;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * @author Yuyuan Huang
 * @create 2021-04-05 17:18
 */
public interface DriverPagingRepository extends PagingAndSortingRepository<Driver, UUID> {

}
