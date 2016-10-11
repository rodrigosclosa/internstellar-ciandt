package com.ciandt.internstellarapi.service.validator;

import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.entity.PerguntaOpcao;
import com.ciandt.internstellarapi.entity.Resposta;
import com.ciandt.internstellarapi.entity.Token;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.GrupoService;
import com.ciandt.internstellarapi.service.PerguntaService;
import com.ciandt.internstellarapi.service.TokenService;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.repackaged.com.google.common.base.StringUtil;

import java.util.List;

/**
 * Created by helder on 10/10/16.
 */

public class RespostaValidator {

    private TokenService tokenService;

    private PerguntaService perguntaService;

    private GrupoService grupoService;

    public RespostaValidator() {
        tokenService = new TokenService();
        perguntaService = new PerguntaService();
        grupoService = new GrupoService();
    }

    private boolean validarRespostaInformada(Resposta resposta) throws BadRequestException {
        if (resposta == null) {
            throw new BadRequestException(Messages.RespostaMessages.RESPOSTA_NAO_INFORMADA);
        }
        return Boolean.TRUE;
    }

    private boolean validarGrupoInformado(Long idGrupo) throws BadRequestException {
        if (idGrupo == null) {
            throw new BadRequestException(Messages.RespostaMessages.GRUPO_NAO_INFORMADO);
        }
        return Boolean.TRUE;
    }

    private boolean validarTokenInformado(String token) throws BadRequestException {
        if (StringUtil.isEmptyOrWhitespace(token)) {
            throw new BadRequestException(Messages.RespostaMessages.TOKEN_NAO_INFORMADO);
        }
        return Boolean.TRUE;
    }

    private boolean validarPerguntaInformada(Long idPergunta) throws BadRequestException {
        if (idPergunta == null) {
            throw new BadRequestException(Messages.RespostaMessages.PERGUNTA_NAO_INFORMADA);
        }
        return Boolean.TRUE;
    }

    private boolean validarRespostaCorretaInformada(String idResposta) throws BadRequestException {
        if (StringUtil.isEmptyOrWhitespace(idResposta)) {
            throw new BadRequestException(Messages.RespostaMessages.RESPOSTA_CORRETA_NAO_INFORMADA);
        }
        return Boolean.TRUE;
    }

    private boolean validarPerguntaRespostaValida(Resposta resposta) throws BadRequestException {
        try {
            Pergunta pergunta;
            pergunta = perguntaService.getById(resposta.getIdPergunta());
            verificarRespostaInformadaExistente(pergunta.getOpcoes(), resposta.getIdResposta());

        } catch (NotFoundException e) {
            throw new BadRequestException(Messages.RespostaMessages.PERGUNTA_INFORMADA_INVALIDA);
        }
        return Boolean.TRUE;
    }

    private void verificarRespostaInformadaExistente(List<PerguntaOpcao> opcoes, String resposta) throws BadRequestException {
        Boolean respostaValida = Boolean.FALSE;
        for (PerguntaOpcao opcao : opcoes) {
            if (opcao.getIdOpcao().equals(resposta)) {
                respostaValida = Boolean.TRUE;
            }
        }
        if (!respostaValida) {
            throw new BadRequestException(Messages.RespostaMessages.RESPOSTA_INVALIDA_PARA_A_PERGUNTA);
        }
    }

    private boolean validarTokenValido(String token) throws UnauthorizedException {
        tokenService.getByToken(token);
        return Boolean.TRUE;
    }

    private boolean validarTokenGrupo(String tokenRequest, Long idGrupo) throws UnauthorizedException {
        Token token = tokenService.getByToken(tokenRequest);
        if (!token.getIdGrupo().equals(idGrupo)) {
            throw new UnauthorizedException(Messages.RespostaMessages.TOKEN_INVALIDO_PARA_GRUPO);
        }
        return Boolean.TRUE;
    }

    private boolean validarGrupoValido(Long idGrupo) throws BadRequestException {
        Grupo grupo = null;
        try {
            grupo = grupoService.getById(idGrupo);
        } catch (NotFoundException e) {
            throw new BadRequestException(Messages.RespostaMessages.GRUPO_INFORMADO_INVALIDO);
        }
        return Boolean.TRUE;
    }

    public boolean validar(Resposta resposta) throws UnauthorizedException, BadRequestException {
        return validarRespostaInformada(resposta)
                && validarTokenInformado(resposta.getToken())
                && validarTokenValido(resposta.getToken())
                && validarGrupoInformado(resposta.getIdGrupo())
                && validarGrupoValido(resposta.getIdGrupo())
                && validarTokenGrupo(resposta.getToken(), resposta.getIdGrupo())
                && validarPerguntaInformada(resposta.getIdPergunta())
                && validarRespostaCorretaInformada(resposta.getIdResposta())
                && validarPerguntaRespostaValida(resposta);
    }
}
