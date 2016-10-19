package com.ciandt.internstellarapi.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by helder on 17/10/16.
 */
@Entity
public class PerguntaGrupo extends BaseEntity {

    @Index
    private Long idPergunta;

    @Index
    private Long idPlaneta;

    @Index
    private Long idGrupo;

    private Integer quantidadeTentativas;

    @Index
    private Boolean respondida;

    public Long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Long idPergunta) {
        this.idPergunta = idPergunta;
    }

    public Long getIdPlaneta() {
        return idPlaneta;
    }

    public void setIdPlaneta(Long idPlaneta) {
        this.idPlaneta = idPlaneta;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getQuantidadeTentativas() {
        return quantidadeTentativas;
    }

    public void setQuantidadeTentativas(Integer quantidadeTentativas) {
        this.quantidadeTentativas = quantidadeTentativas;
    }

    public Boolean getRespondida() {
        return respondida;
    }

    public void setRespondida(Boolean respondida) {
        this.respondida = respondida;
    }
}
