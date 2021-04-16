package com.compass.repository;

import com.compass.domain.Driver;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Yuyuan Huang
 * @create 2021-03-20 13:43
 */
@Repository
public interface DriverRepository extends CrudRepository<Driver,Long> {
//    List<Driver> findAll(PageRequest pageRequest);


    List<List<Object>> findIdAndNameOrderByIncomeAtDesc();
    @Modifying
    @Query("update Driver d set d.accountStatus = :i,d.bossId = :bossId where d.id = :id")
    void updateAccountStatusBossIdById(@Param(value = "id") long id,@Param(value="i") int i,@Param(value = "bossId") UUID bossId);

    List<List<Object>> findIdAndDriverNameByBossId(UUID bossId);

    Optional<Driver> findByBossId(UUID BossId);

    @Modifying
    @Query("select * from Car c where id = :id,bossId = :bossId")
    Optional<Driver> findDriverByIdAndBossId(UUID id,UUID bossId);

}
