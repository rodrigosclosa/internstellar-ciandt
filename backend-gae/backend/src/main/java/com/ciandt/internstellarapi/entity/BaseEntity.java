package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Id;

/**
 * Created by helder on 07/10/16.
 */

public abstract class BaseEntity {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
