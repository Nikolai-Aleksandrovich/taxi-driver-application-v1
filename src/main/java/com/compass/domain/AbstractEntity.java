package com.compass.domain;

import org.springframework.data.domain.Persistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

/**
 * @author Yuyuan Huang
 * @create 2021-04-08 12:17
 */
@MappedSuperclass
public abstract class AbstractEntity<ID>implements Persistable<ID> {
    @Transient
    private boolean isNew = true;
    //默认设置为true

    @Override
    public boolean isNew(){
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew(){
        this.isNew = false;
    }

}
