package com.ciandt.internstellarapi.entity;

import javax.annotation.Nullable;

/**
 * Created by helder on 07/10/16.
 */
public class PerguntaOpcao {
    private Integer codigo;
    private String opcao;
    private String descricao;
    @Nullable
    private Boolean correta;

    public PerguntaOpcao() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
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
