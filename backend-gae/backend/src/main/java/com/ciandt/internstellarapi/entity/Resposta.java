package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import javax.annotation.Nullable;

/**
 * Created by helder on 10/10/16.
 */
@Entity
public class Resposta extends BaseEntity {

    @Index
    private Long idGrupo;

    @Ignore
    @Nullable
    private Grupo grupo;

    @Index
    private Long idPergunta;

    @Ignore
    @Nullable
    private Pergunta pergunta;

    private String idResposta;

    @Ignore
    private String token;

    public Resposta() {
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Long idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(String idResposta) {
        this.idResposta = idResposta;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Nullable
    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(@Nullable Grupo grupo) {
        this.grupo = grupo;
    }

    @Nullable
    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(@Nullable Pergunta pergunta) {
        this.pergunta = pergunta;
    }
}
