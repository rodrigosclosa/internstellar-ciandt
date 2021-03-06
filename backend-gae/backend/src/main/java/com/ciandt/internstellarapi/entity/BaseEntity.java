package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

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

    @Override
    public boolean equals(Object o) {
        return this.id.equals(((BaseEntity)o).getId());
    }
}
