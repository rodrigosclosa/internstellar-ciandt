package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;

import java.util.List;

/**
 * Created by helder on 07/10/16.
 */
@Entity
public class Pergunta extends BaseEntity {

    private String titulo;

    private String descricao;

    private List<PerguntaOpcao> opcoes;

    private Long planetaId;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PerguntaOpcao> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<PerguntaOpcao> opcoes) {
        this.opcoes = opcoes;
    }

    public Long getPlanetaId() {
        return planetaId;
    }

    public void setPlanetaId(Long planetaId) {
        this.planetaId = planetaId;
    }
}
