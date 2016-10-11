package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by helder on 07/10/16.
 */
@Entity
public class Pergunta extends BaseEntity {

    private String titulo;

    private String descricao;

    private List<PerguntaOpcao> opcoes;

    @Index
    private Long planetaId;

    @Ignore
    @Nullable
    private Planeta planeta;

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

    @Nullable
    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(@Nullable Planeta planeta) {
        this.planeta = planeta;
    }
}
