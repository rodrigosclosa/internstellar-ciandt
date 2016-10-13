package com.ciandt.internstellarapi.entity;


import java.io.Serializable;

/**
 * Created by helder on 13/10/16.
 */
public class GrupoSumarioAvaliacao implements Serializable {

    private Grupo grupo;

    private Integer countRespCorr;

    private Integer desafConc;

    public GrupoSumarioAvaliacao() {
        countRespCorr = 0;
        desafConc = 0;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Integer getCountRespCorr() {
        return countRespCorr;
    }

    public void setCountRespCorr(Integer countRespCorr) {
        this.countRespCorr = countRespCorr;
    }

    public Integer getDesafConc() {
        return desafConc;
    }

    public void setDesafConc(Integer desafConc) {
        this.desafConc = desafConc;
    }
}
