package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by falcao on 06/10/16.
 */
@Entity
public class Integrante {

    @Id
    private Long id;

    private String nome;

    private String foto;

    private Integer numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
