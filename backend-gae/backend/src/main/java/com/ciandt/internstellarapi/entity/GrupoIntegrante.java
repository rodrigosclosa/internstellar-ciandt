package com.ciandt.internstellarapi.entity;

import com.google.appengine.api.datastore.Text;

/**
 * Created by rodrigosclosa on 05/10/16.
 */

public class GrupoIntegrante {
    private Integer numero;
    private String nome;
    private Text foto;

    public GrupoIntegrante() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Text getFoto() {
        return foto;
    }

    public void setFoto(Text foto) {
        this.foto = foto;
    }
}
