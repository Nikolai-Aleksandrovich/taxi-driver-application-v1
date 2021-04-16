package com.compass.repository;

import com.compass.domain.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Yuyuan Huang
 * @create 2021-03-18 10:58
 */
@Repository
public interface CarRepository extends CrudRepository<Car,Long> {
    @Modifying
    @Query("update Car c set c.createdBy = :createdBy where c.id = :id")
    void updateCreatedByById(@Param("createdBy") String createdBy,@Param("id") Long id);

    @Modifying
    @Query("select * from Car c where c.bossId = :bossId")
    List<Car> findAllByBossId(@Param("bossId") UUID BossId);

    Car findByPlateNumber(String plateNumber);


    UUID findIdByPlateNumber(String plateNumber);
}
