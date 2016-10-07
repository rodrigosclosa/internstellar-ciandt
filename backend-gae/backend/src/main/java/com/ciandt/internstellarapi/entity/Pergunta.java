package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import java.util.List;

/**
 * Created by helder on 07/10/16.
 */
@Entity
public class Pergunta extends BaseEntity {

    private String titulo;

    private String descricao;

    private String opcaoCorreta;

    private List<PerguntaOpcao> opcoes;

    @Index
    private Long planetaId;

    public Pergunta() {
    }

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

    public String getOpcaoCorreta() {
        return opcaoCorreta;
    }

    public void setOpcaoCorreta(String opcaoCorreta) {
        this.opcaoCorreta = opcaoCorreta;
    }
}
