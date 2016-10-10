package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by helder on 10/10/16.
 */
@Entity
public class Resposta extends BaseEntity {

    @Index
    private Long idGrupo;

    @Index
    private Long idPergunta;

    private String idResposta;

    @Ignore
    private String token;

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
}
