package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Index;

import javax.annotation.Nullable;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * Created by helder on 07/10/16.
 */
public class PerguntaOpcao {

    @Index
    private String idOpcao;
    private Integer codigo;
    private String descricao;
    @Nullable
    private Boolean correta;

    public PerguntaOpcao() {
    }

    public String getIdOpcao() {
        return idOpcao;
    }

    public void setIdOpcao(String idOpcao) {
        this.idOpcao = idOpcao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Nullable
    public Boolean getCorreta() {
        return correta;
    }

    public void setCorreta(@Nullable Boolean correta) {
        this.correta = correta;
    }
}
