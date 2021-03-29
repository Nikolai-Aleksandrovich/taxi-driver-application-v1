package com.compass.repository;

import com.compass.domain.Poi;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Yuyuan Huang
 * @create 2021-03-24 20:16
 */
public interface PoiRepository extends CrudRepository<Poi,Long> {
}
