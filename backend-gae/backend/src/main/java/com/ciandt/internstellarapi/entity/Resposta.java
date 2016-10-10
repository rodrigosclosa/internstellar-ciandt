package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by helder on 10/10/16.
 */
@Entity
public class Resposta extends BaseEntity {

    private Long idGrupo;

    private Long idPergunta;

    private String idResposta;

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
}
