package com.ciandt.internstellarapi.service.validator;

import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.entity.PerguntaOpcao;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.PlanetaService;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.repackaged.com.google.common.base.StringUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by helder on 07/10/16.
 */

public class PerguntaValidator {

    private PlanetaService planetaService;

    public PerguntaValidator() {
        planetaService = new PlanetaService();
    }

    private Boolean validarPerguntaInformada(Pergunta pergunta) throws BadRequestException {
        if (pergunta == null) {
            throw new BadRequestException(Messages.PerguntaMessages.PERGUNTA_NAO_INFORMADA);
        }
        return Boolean.TRUE;
    }

    private Boolean validarTituloInformado(String titulo) throws BadRequestException {
        if (StringUtil.isEmptyOrWhitespace(titulo)) {
            throw new BadRequestException(Messages.PerguntaMessages.TITULO_PERGUNTA_NAO_INFORMADO);
        }
        return Boolean.TRUE;
    }

    private Boolean validarDescricaoInformada(String descricao) throws BadRequestException {
        if (StringUtil.isEmptyOrWhitespace(descricao)) {
            throw new BadRequestException(Messages.PerguntaMessages.DESCRICAO_PERGUNTA_NAO_INFORMADA);
        }
        return Boolean.TRUE;
    }

    private Boolean validarOpcoesInformadas(List<PerguntaOpcao> opcoes) throws BadRequestException {
        if (opcoes == null || opcoes.isEmpty()) {
            throw new BadRequestException(Messages.PerguntaMessages.OPCOES_PERGUNTA_NAO_INFORMADAS);
        }
        return Boolean.TRUE;
    }

    private Boolean validarPlanetaInformado(Long idPlaneta) throws BadRequestException {
        if (idPlaneta == null) {
            throw new BadRequestException(Messages.PerguntaMessages.PLANETA_DEVE_SER_INFORMADO);
        }
        return Boolean.TRUE;
    }

    private Boolean validarPlaneta(Long idPLaneta) throws BadRequestException {
        try {
            planetaService.getById(idPLaneta);
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new BadRequestException(Messages.PerguntaMessages.PLANETA_INFORMADO_INVALIDO);
        }
        return Boolean.TRUE;
    }

    private Boolean validarOpcoes(List<PerguntaOpcao> opcoes) throws BadRequestException {
        for (PerguntaOpcao po : opcoes) {
            if (StringUtil.isEmptyOrWhitespace(po.getDescricao())) {
                throw new BadRequestException(Messages.PerguntaMessages.DESCRICAO_OPCAO_DEVE_SER_INFORMADA);
            }
        }
        return true;
    }

    private Boolean validarOpcaoCorretaInformada(List<PerguntaOpcao> opcoes) throws BadRequestException {
        Boolean respostaInformada = Boolean.FALSE;
        for (PerguntaOpcao opcao : opcoes) {
            if (Boolean.TRUE.equals(opcao.getCorreta())) {
                respostaInformada = Boolean.TRUE;
                break;
            }
        }
        if (!respostaInformada) {
            throw new BadRequestException(Messages.PerguntaMessages.OPCAO_CORRETA_NAO_INFORMADA);
        }
        return Boolean.TRUE;
    }


    public Boolean validar(Pergunta pergunta) throws BadRequestException {
        return validarPerguntaInformada(pergunta)
                && validarTituloInformado(pergunta.getTitulo())
                && validarDescricaoInformada(pergunta.getDescricao())
                && validarOpcoesInformadas(pergunta.getOpcoes())
                && validarPlanetaInformado(pergunta.getPlanetaId())
                && validarPlaneta(pergunta.getPlanetaId())
                && validarOpcoes(pergunta.getOpcoes())
                && validarOpcaoCorretaInformada(pergunta.getOpcoes());

    }
}
