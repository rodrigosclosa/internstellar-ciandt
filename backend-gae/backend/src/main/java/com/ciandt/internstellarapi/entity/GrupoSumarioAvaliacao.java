package com.ciandt.internstellarapi.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by helder on 13/10/16.
 */
public class GrupoSumarioAvaliacao implements Serializable {

    private Grupo grupo;

    private Integer countRespCorr;

    private Integer desafConc;

    private Long tempoTotalRespostas;

    private Long tempoTotalDesafios;

    private Integer pontos;

    private Long ultimaPontuacao;

    private String ultimaPontuacaoFormatada;

    public GrupoSumarioAvaliacao() {
        countRespCorr = 0;
        desafConc = 0;
        tempoTotalDesafios = 0L;
        tempoTotalRespostas = 0L;
        ultimaPontuacao = 0L;
        ultimaPontuacaoFormatada = "";
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

    public Long getTempoTotalRespostas() {
        return tempoTotalRespostas;
    }

    public void setTempoTotalRespostas(Long tempoTotalRespostas) {
        this.tempoTotalRespostas = tempoTotalRespostas;
    }

    public Long getTempoTotalDesafios() {
        return tempoTotalDesafios;
    }

    public void setTempoTotalDesafios(Long tempoTotalDesafios) {
        this.tempoTotalDesafios = tempoTotalDesafios;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Long getUltimaPontuacao() {
        return ultimaPontuacao;
    }

    public void setUltimaPontuacao(Long ultimaPontuacao) {
        this.ultimaPontuacao = ultimaPontuacao;
    }

    public String getUltimaPontuacaoFormatada() {
        return ultimaPontuacaoFormatada;
    }

    public void setUltimaPontuacaoFormatada(String ultimaPontuacaoFormatada) {
        this.ultimaPontuacaoFormatada = ultimaPontuacaoFormatada;
    }
}
