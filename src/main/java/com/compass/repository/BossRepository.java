package com.compass.repository;

import com.compass.domain.Boss;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Yuyuan Huang
 * @create 2021-03-19 18:00
 */
public interface BossRepository extends CrudRepository<Boss,Long> {
    Boss findBossByBossName(String name);
}
