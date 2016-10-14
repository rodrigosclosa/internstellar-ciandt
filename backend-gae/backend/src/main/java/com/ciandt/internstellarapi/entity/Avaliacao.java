package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

/**
 * Created by helder on 11/10/16.
 */
@Entity
public class Avaliacao extends BaseEntity implements DataControl {

    @Index
    private Long idGrupo;

    private String loginSensei;

    private Integer desafio;

    private String notes;


    private Long data;

    private String dataFormatada;


    public Avaliacao() {
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getLoginSensei() {
        return loginSensei;
    }

    public void setLoginSensei(String loginSensei) {
        this.loginSensei = loginSensei;
    }

    public Integer getDesafio() {
        return desafio;
    }

    public void setDesafio(Integer desafio) {
        this.desafio = desafio;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public Long getData() {
        return data;
    }
    @Override
    public void setData(Long data) {
        this.data = data;
    }

    @Override
    public String getDataFormatada() {
        return dataFormatada;
    }

    @Override
    public void setDataFormatada(String dataFormatada) {
        this.dataFormatada = dataFormatada;
    }
}
