package com.compass.repository;

import com.compass.domain.DriverIncome;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Yuyuan Huang
 * @create 2021-03-29 17:35
 */
public interface DriverIncomeRepository extends CrudRepository<DriverIncome,Long> {
        List<Long> findIdByBossIdOrderByIncome(Long bossId);
}
