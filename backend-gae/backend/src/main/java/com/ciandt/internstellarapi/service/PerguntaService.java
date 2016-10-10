package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.PerguntaDao;
import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.entity.PerguntaOpcao;
import com.ciandt.internstellarapi.entity.Planeta;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.validator.PerguntaValidator;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by helder on 07/10/16.
 */

public class PerguntaService {

    private static final int CODIGO_INICIAL_PERGUNTA = 1;

    private PerguntaDao perguntaDao;

    private PerguntaValidator perguntaValidator;

    public PerguntaService() {
        perguntaDao = new PerguntaDao();
        perguntaValidator = new PerguntaValidator();
    }

    public List<Pergunta> list() {
        return perguntaDao.listAll();
    }

    public Pergunta getById(Long id) throws NotFoundException {
        Pergunta item = null;

        item = perguntaDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.PerguntaMessages.PERGUNTA_NAO_ENCONTRADA);
        }

        return item;
    }

    public List<Pergunta> findByPlaneta(Long idPlaneta) throws NotFoundException {
        List<Pergunta> perguntasResult = null;
        perguntasResult = perguntaDao.listByProperty("planetaId", idPlaneta);
        if (perguntasResult == null || perguntasResult.isEmpty()) {
            throw new NotFoundException(Messages.PerguntaMessages.NENHUMA_PERGUNTA_FOI_ENCONTRADA);
        }
        Iterator<Pergunta> perguntasIterator = perguntasResult.iterator();
        while (perguntasIterator.hasNext()) {
            configurarOpcoes(perguntasIterator.next().getOpcoes());
        }
        return perguntasResult;
    }

    private void configurarOpcoes(List<PerguntaOpcao> opcoes) {
        embaralharOpcoes(opcoes);
        configurarCodigoOpcoes(opcoes);
    }

    private void configurarCodigoOpcoes(List<PerguntaOpcao> opcoes) {
        Iterator<PerguntaOpcao> opcoesIterator = opcoes.iterator();
        int codigo = CODIGO_INICIAL_PERGUNTA;
        while (opcoesIterator.hasNext()) {
            opcoesIterator.next().setCodigo(codigo++);
        }
    }

    private void embaralharOpcoes(List<PerguntaOpcao> opcoes) {
        Collections.sort(opcoes, new RamdomOrder());
    }

    private class RamdomOrder implements Comparator<PerguntaOpcao> {

        @Override
        public int compare(PerguntaOpcao perguntaOpcao, PerguntaOpcao t1) {
            return Double.valueOf(Math.random()).compareTo( Math.random());
        }
    }


    public Pergunta insert(Pergunta item) throws BadRequestException {

        perguntaValidator.validar(item);
        configPergunta(item);
        perguntaDao.insert(item);

        return item;
    }

    public Pergunta update(Pergunta item) throws BadRequestException, NotFoundException {

        perguntaValidator.validar(item);
        Pergunta u = perguntaDao.getById(item.getId());
        if (u == null) {
            throw new NotFoundException(Messages.PerguntaMessages.PERGUNTA_NAO_ENCONTRADA);
        }
        configPergunta(item);
        perguntaDao.update(item);

        return item;
    }

    private void configPergunta(Pergunta pergunta) {
        for (PerguntaOpcao opcao : pergunta.getOpcoes()) {
            opcao.setIdOpcao(UUID.randomUUID().toString().replace("-", ""));
        }
    }

    public void remove(Long id) throws NotFoundException {

        Pergunta item = perguntaDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.PerguntaMessages.PERGUNTA_NAO_ENCONTRADA);
        }

        perguntaDao.delete(item);
    }

}
