package com.compass.repository;

import com.compass.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yuyuan Huang
 * @create 2021-03-19 17:05
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByName(String name);

}
