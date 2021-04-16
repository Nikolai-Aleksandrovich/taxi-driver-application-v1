package com.compass.repository;

import com.compass.domain.DriverIncome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Yuyuan Huang
 * @create 2021-03-29 17:35
 */
@Repository
public interface DriverIncomeRepository extends CrudRepository<DriverIncome,Long> {
        List<Long> findIdByBossIdOrderByIncome(UUID bossId);
}
