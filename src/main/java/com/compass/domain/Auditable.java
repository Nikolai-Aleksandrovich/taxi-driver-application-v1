package com.compass.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
//AuditingEntityListener标签开启后，下面的时间标签才会生效。
//然后还需要在启动类加上@EnableJpaAuditing注解。
public class Auditable<U> {


    @CreatedDate
    @Column(name = "created_date")
    private Long createdTime;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Long updateTime;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private U lastModifiedBy;

    @CreatedBy
    @Column(name = "created_by")
    private U createBy;
}
