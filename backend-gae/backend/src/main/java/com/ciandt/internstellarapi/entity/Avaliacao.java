package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by helder on 11/10/16.
 */
@Entity
public class Avaliacao extends BaseEntity {

    @Index
    private Long idGrupo;

    private String idAvaliador;

    private AvaliadorAvaliacao avaliador;

    private Integer desafio;

    private String notes;

    public Avaliacao() {
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getIdAvaliador() {
        return idAvaliador;
    }

    public void setIdAvaliador(String idAvaliador) {
        this.idAvaliador = idAvaliador;
    }

    public AvaliadorAvaliacao getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(AvaliadorAvaliacao avaliador) {
        this.avaliador = avaliador;
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
}
