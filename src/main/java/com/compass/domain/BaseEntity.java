package com.compass.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Yuyuan Huang
 * @create 2021-04-05 14:45
 */
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
//AuditingEntityListener标签开启后，下面的时间标签才会生效。
//然后还需要在启动类加上@EnableJpaAuditing注解。
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @CreatedDate
    private Long CreatedTime;

    @LastModifiedDate
    private Long updateTime;

    @LastModifiedBy
    private UUID modifyAccountId;

    @CreatedBy
    private UUID createAccountId;
}
