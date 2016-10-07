package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by helder on 07/10/16.
 */

@Entity
public class Planeta extends BaseEntity {

    private String nome;

    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
