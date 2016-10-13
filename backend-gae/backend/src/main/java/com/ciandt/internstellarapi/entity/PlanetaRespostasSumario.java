package com.ciandt.internstellarapi.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by helder on 13/10/16.
 */
public class PlanetaRespostasSumario {

    private Planeta planeta;

    private List<PlanetaSumarioGrupo> grupos;

    private Integer respostasEnviadas;

    private Integer respostasCorretas;

    private Map<Integer,Grupo> rankingGruposPeloPlaneta;

    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }

    public List<PlanetaSumarioGrupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<PlanetaSumarioGrupo> grupos) {
        this.grupos = grupos;
    }

    public Integer getRespostasEnviadas() {
        return respostasEnviadas;
    }

    public void setRespostasEnviadas(Integer respostasEnviadas) {
        this.respostasEnviadas = respostasEnviadas;
    }

    public Integer getRespostasCorretas() {
        return respostasCorretas;
    }

    public void setRespostasCorretas(Integer respostasCorretas) {
        this.respostasCorretas = respostasCorretas;
    }

    public Map<Integer, Grupo> getRankingGruposPeloPlaneta() {
        return rankingGruposPeloPlaneta;
    }

    public void setRankingGruposPeloPlaneta(Map<Integer, Grupo> rankingGruposPeloPlaneta) {
        this.rankingGruposPeloPlaneta = rankingGruposPeloPlaneta;
    }
}
