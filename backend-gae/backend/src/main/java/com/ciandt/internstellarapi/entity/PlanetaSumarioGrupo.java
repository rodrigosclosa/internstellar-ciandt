package com.ciandt.internstellarapi.entity;

/**
 * Created by helder on 13/10/16.
 */

public class PlanetaSumarioGrupo {

    private Grupo grupo;

    private Integer respostasInformadas;

    private Integer respostasCorretas;

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Integer getRespostasInformadas() {
        return respostasInformadas;
    }

    public void setRespostasInformadas(Integer respostasInformadas) {
        this.respostasInformadas = respostasInformadas;
    }

    public Integer getRespostasCorretas() {
        return respostasCorretas;
    }

    public void setRespostasCorretas(Integer respostasCorretas) {
        this.respostasCorretas = respostasCorretas;
    }
}
