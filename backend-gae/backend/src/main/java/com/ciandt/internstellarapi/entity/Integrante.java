package com.ciandt.internstellarapi.entity;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by falcao on 06/10/16.
 */
@Entity
public class Integrante extends BaseEntity {

    private String nome;

    private Text foto;

    private Integer numero;

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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
