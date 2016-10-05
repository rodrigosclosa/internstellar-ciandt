package com.ciandt.internstellarapi.entity;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by rodrigosclosa on 05/10/16.
 */
@Entity
public class Equipe {
    @Id
    private Long id;
    @Index
    private String nome;
    @Index
    private String cor;
    @Index
    private String base;
    private String imagem;

    public Equipe() {
    }

    public Equipe(String nome, String cor, String base) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.base = base;
    }

    public Equipe(String nome, String cor, String base, String imagem) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.base = base;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
