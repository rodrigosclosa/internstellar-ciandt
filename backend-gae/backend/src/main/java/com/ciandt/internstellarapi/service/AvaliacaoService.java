package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.AvaliacaoDao;
import com.ciandt.internstellarapi.entity.Avaliacao;
import com.ciandt.internstellarapi.entity.Avaliacao;
import com.ciandt.internstellarapi.entity.Equipe;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.validator.AvaliacaoValidator;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;

import java.util.List;

/**
 * Created by helder on 11/10/16.
 */
public class AvaliacaoService {

    private AvaliacaoDao avaliacaoDao;

    private AvaliacaoValidator avaliacaoValidator;

    public AvaliacaoService() {
        avaliacaoDao = new AvaliacaoDao();
        avaliacaoValidator = new AvaliacaoValidator();
    }

    public List<Avaliacao> list() {
        return avaliacaoDao.listAll();
    }

    public Avaliacao getById(Long id) throws NotFoundException {
        Avaliacao item = null;

        item = avaliacaoDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.AvaliacaoMessages.AVALIACAO_NAO_ENCONTRADA);
        }

        return item;
    }

    public Avaliacao insert(Avaliacao item) throws BadRequestException {

        avaliacaoValidator.validar(item);

        avaliacaoDao.insert(item);

        return item;
    }

    public Avaliacao update(Avaliacao item) throws NotFoundException, BadRequestException {

        avaliacaoValidator.validar(item);

        Avaliacao u = avaliacaoDao.getById(item.getId());

        if (u == null) {
            throw new NotFoundException(Messages.AvaliacaoMessages.AVALIACAO_NAO_ENCONTRADA);
        }

        avaliacaoDao.update(item);

        return item;
    }

    public List<Avaliacao> findByGrupo(Long idGrupo) {
        List<Avaliacao> avaliacoes;
        avaliacoes = avaliacaoDao.listByProperty("idGrupo", idGrupo);
        return avaliacoes;
    }

    public void remove(Long id) throws NotFoundException {

        Avaliacao item = avaliacaoDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.AvaliacaoMessages.AVALIACAO_NAO_ENCONTRADA);
        }

        avaliacaoDao.delete(item);
    }


}
