package com.compass.repository;

import com.compass.domain.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yuyuan Huang
 * @create 2021-03-24 18:15
 */
@Repository
public interface DestinationRepository extends CrudRepository<Destination,Long> {
//    Destination save(Destination destination);

}
