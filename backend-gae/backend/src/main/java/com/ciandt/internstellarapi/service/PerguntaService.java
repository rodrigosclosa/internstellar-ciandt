package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.PerguntaDao;
import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.entity.Planeta;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.validator.PerguntaValidator;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;

import java.util.List;

/**
 * Created by helder on 07/10/16.
 */

public class PerguntaService {

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
        return perguntasResult;
    }

    public Pergunta insert(Pergunta item) throws BadRequestException {

        perguntaValidator.validar(item);

        perguntaDao.insert(item);

        return item;
    }

    public Pergunta update(Pergunta item) throws BadRequestException, NotFoundException {

        perguntaValidator.validar(item);
        Pergunta u = perguntaDao.getById(item.getId());

        if (u == null) {
            throw new NotFoundException(Messages.PerguntaMessages.PERGUNTA_NAO_ENCONTRADA);
        }

        perguntaDao.update(item);

        return item;
    }

    public void remove(Long id) throws NotFoundException {

        Pergunta item = perguntaDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.PerguntaMessages.PERGUNTA_NAO_ENCONTRADA);
        }

        perguntaDao.delete(item);
    }

}
