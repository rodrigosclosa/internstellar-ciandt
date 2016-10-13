package com.ciandt.internstellarapi.entity;


import java.io.Serializable;

/**
 * Created by helder on 13/10/16.
 */
public class GrupoSumarioAvaliacao implements Serializable {

    private Grupo grupo;

    private Integer countRespostasCorretas;

    private Integer desafiosConcluídos;

    public GrupoSumarioAvaliacao(){
        countRespostasCorretas = 0;
        desafiosConcluídos = 0;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Integer getCountRespostasCorretas() {
        return countRespostasCorretas;
    }

    public void setCountRespostasCorretas(Integer countRespostasCorretas) {
        this.countRespostasCorretas = countRespostasCorretas;
    }

    public Integer getDesafiosConcluídos() {
        return desafiosConcluídos;
    }

    public void setDesafiosConcluídos(Integer desafiosConcluídos) {
        this.desafiosConcluídos = desafiosConcluídos;
    }
}
