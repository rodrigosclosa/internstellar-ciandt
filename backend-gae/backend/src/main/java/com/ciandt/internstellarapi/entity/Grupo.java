package com.ciandt.internstellarapi.entity;

import com.ciandt.internstellarapi.endpoint.EquipeEndpoint;
import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import java.util.List;

/**
 * Created by rodrigosclosa on 05/10/16.
 */
@Entity
public class Grupo {
    @Id
    private Long id;
    @Index
    private Long idEquipe;
    @Ignore
    private Equipe equipe;

    private List<GrupoIntegrante> integrantes;
    private Text fotoEquipe;

    public Grupo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<GrupoIntegrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<GrupoIntegrante> integrantes) {
        this.integrantes = integrantes;
    }

    public Text getFotoEquipe() {
        return fotoEquipe;
    }

    public void setFotoEquipe(Text fotoEquipe) {
        this.fotoEquipe = fotoEquipe;
    }
}
