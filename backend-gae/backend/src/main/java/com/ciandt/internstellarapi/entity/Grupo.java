package com.ciandt.internstellarapi.entity;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigosclosa on 05/10/16.
 */
@Entity
public class Grupo extends BaseEntity {

    @Index
    private Long idEquipe;

    @Ignore
    private Equipe equipe;

    @Index
    private String senha;

    @Ignore
    private String senhaVerificadora;

    @Ignore
    private List<Integrante> integrantes;

    private List<Long> idIntegrantes;

    private Text fotoEquipe;

    @Ignore
    private String token;

    public Grupo() {
        this.integrantes = new ArrayList<Integrante>();
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }

    public void addIntegrantes(Integrante integrante) {
        this.integrantes.add(integrante);
    }

    public Text getFotoEquipe() {
        return fotoEquipe;
    }

    public void setFotoEquipe(Text fotoEquipe) {
        this.fotoEquipe = fotoEquipe;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaVerificadora() {
        return senhaVerificadora;
    }

    public void setSenhaVerificadora(String senhaVerificadora) {
        this.senhaVerificadora = senhaVerificadora;
    }

    public List<Long> getIdIntegrantes() {
        return idIntegrantes;
    }

    public void setIdIntegrantes(List<Long> idIntegrantes) {
        this.idIntegrantes = idIntegrantes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
