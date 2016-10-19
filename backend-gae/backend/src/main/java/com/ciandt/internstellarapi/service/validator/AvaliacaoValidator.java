package com.ciandt.internstellarapi.service.validator;

import com.ciandt.internstellarapi.dao.AvaliacaoDao;
import com.ciandt.internstellarapi.entity.Avaliacao;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.GrupoService;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;

import org.omg.CORBA.INTERNAL;

/**
 * Created by helder on 11/10/16.
 */
public class AvaliacaoValidator {

    private GrupoService grupoService;

    private AvaliacaoDao avaliacaoDao;

    public AvaliacaoValidator(){
        grupoService = new GrupoService();
        avaliacaoDao = new AvaliacaoDao();
    }

    public Boolean validarGrupoInformado(Long idGrupo) throws BadRequestException {
        if (idGrupo == null) {
            throw new BadRequestException(Messages.AvaliacaoMessages.GRUPO_NAO_INFORMADO);
        }
        return Boolean.TRUE;
    }

    public Boolean validarDesafioInformado(Integer desafio) throws BadRequestException {
        if (desafio == null) {
            throw new BadRequestException(Messages.AvaliacaoMessages.DESAFIO_NAO_INFORMADO);
        }
        return Boolean.TRUE;
    }

    public Boolean validarGrupoValido(Long idGrupo) throws BadRequestException {
        try {
            grupoService.getById(idGrupo);
        } catch (NotFoundException e) {
            throw new BadRequestException(Messages.AvaliacaoMessages.GRUPO_INFORMADO_INVALIDO);
        }
        return Boolean.TRUE;
    }

    public  Boolean validarDesafioJaAvaliadoParaGrupo(Integer desafio, Long idGrupo) throws BadRequestException {
        Avaliacao avaliacao = avaliacaoDao.findByDesafioAndGrupo(desafio, idGrupo);

        if(avaliacao != null){
            throw new BadRequestException(Messages.AvaliacaoMessages.AVALIACAO_JA_ENVIADA_PARA_O_GRUPO);
        }
        return Boolean.TRUE;
    }

    public Boolean validar(Avaliacao avaliacao) throws BadRequestException {
        return validarGrupoInformado(avaliacao.getIdGrupo())
                && validarGrupoValido(avaliacao.getIdGrupo())
                && validarDesafioInformado(avaliacao.getDesafio())
                && validarDesafioJaAvaliadoParaGrupo(avaliacao.getDesafio(), avaliacao.getIdGrupo());
    }
}
