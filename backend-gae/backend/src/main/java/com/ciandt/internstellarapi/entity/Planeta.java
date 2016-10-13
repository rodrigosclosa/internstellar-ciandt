package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import javax.annotation.Nullable;

/**
 * Created by helder on 07/10/16.
 */

@Entity
public class Planeta extends BaseEntity {

    @Index
    private String nome;

    private String descricao;

    private String imagem;

    @Index
    @Nullable
    private Long idGrupoDono;

    @Ignore
    @Nullable
    private Grupo grupoDono;

    public Planeta() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Nullable
    public Long getIdGrupoDono() {
        return idGrupoDono;
    }

    public void setIdGrupoDono(@Nullable Long idGrupoDono) {
        this.idGrupoDono = idGrupoDono;
    }

    @Nullable
    public Grupo getGrupoDono() {
        return grupoDono;
    }

    public void setGrupoDono(@Nullable Grupo grupoDono) {
        this.grupoDono = grupoDono;
    }
}
