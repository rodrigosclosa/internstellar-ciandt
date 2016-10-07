package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.PlanetaDao;
import com.ciandt.internstellarapi.entity.Planeta;
import com.ciandt.internstellarapi.helper.Messages;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helder on 07/10/16.
 */

public class PlanetaService {

    private PlanetaDao planetaDao;

    public PlanetaService() {
        planetaDao = new PlanetaDao();
    }

    public List<Planeta> list() {
        return planetaDao.listAll();
    }

    public List<Planeta> list(String nome) throws NotFoundException {
        List<Planeta> list = new ArrayList<>();

        list = planetaDao.listByProperty("nome", nome);

        if (list == null || list.size() == 0) {
            throw new NotFoundException(String.format(Messages.PlanetaMessages.NAO_FORAM_ENCONTRADOS_PLANETAS_NOME, nome));
        }

        return list;
    }

    public Planeta getById(Long id) throws NotFoundException {
        Planeta item = null;

        item = planetaDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.PlanetaMessages.PLANETA_NAO_ENCONTRADO);
        }

        return item;
    }

    public Planeta insert(Planeta item) throws BadRequestException {

        validarPlaneta(item);

        planetaDao.insert(item);

        return item;
    }

    private void validarPlaneta(Planeta item) throws BadRequestException {
        if (item == null) {
            throw new BadRequestException(Messages.PlanetaMessages.PLANETA_NAO_INFORMADO);
        } else if (item.getNome() == null) {
            throw new BadRequestException(Messages.PlanetaMessages.NOME_PLANETA_NAO_INFORMADO);
        } else if (item.getDescricao() == null) {
            throw new BadRequestException(Messages.PlanetaMessages.DESCRICAO_PLANETA_NAO_INFORMADA);
        }
    }

    public Planeta update(Planeta item) throws BadRequestException, NotFoundException {

        validarPlaneta(item);
        Planeta u = planetaDao.getById(item.getId());

        if (u == null) {
            throw new NotFoundException(Messages.PlanetaMessages.PLANETA_NAO_ENCONTRADO);
        }

        planetaDao.update(item);

        return item;
    }

    public void remove(Long id) throws NotFoundException {

        Planeta item = planetaDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.PlanetaMessages.PLANETA_NAO_ENCONTRADO);
        }

        planetaDao.delete(item);
    }
}
