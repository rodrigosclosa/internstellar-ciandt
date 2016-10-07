package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.EquipeDao;
import com.ciandt.internstellarapi.entity.Equipe;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.util.Constants;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class EquipeService {

    private EquipeDao equipeDao;

    public EquipeService() {
        equipeDao = new EquipeDao();
    }

    public List<Equipe> list() {
        return equipeDao.listAll();
    }

    public List<Equipe> list(String nome) throws NotFoundException {
        List<Equipe> list = new ArrayList<Equipe>();

        list = equipeDao.listByProperty("nome", nome);

        if (list == null || list.size() == 0) {
            throw new NotFoundException(String.format(
                    Messages.EquipeMessages.
                            NAO_FORAM_ENCONTRADAS_EQUIPES_COM_NOME, nome));
        }

        return list;
    }

    public Equipe getById(Long id) throws NotFoundException {
        Equipe item = null;

        item = equipeDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.EquipeMessages.EQUIPE_NAO_ENCONTRADA);
        }

        return item;
    }

    public Equipe insert(Equipe item) throws ConflictException, NotFoundException {

        validarEquipe(item);

        equipeDao.insert(item);

        return item;
    }

    public Equipe update(Equipe item) throws ConflictException, NotFoundException {

        validarEquipe(item);
        Equipe u = equipeDao.getById(item.getId());

        if (u == null) {
            throw new NotFoundException(Messages.EquipeMessages.EQUIPE_NAO_ENCONTRADA);
        }

        equipeDao.update(item);

        return item;
    }

    private void validarEquipe(Equipe item) throws ConflictException {
        if (item == null) {
            throw new ConflictException(Messages.EquipeMessages.EQUIPE_NAO_INFORMADA);
        } else if (item.getNome() == null) {
            throw new ConflictException(Messages.EquipeMessages.NOME_NAO_INFORMADO);
        } else if (item.getCor() == null) {
            throw new ConflictException(Messages.EquipeMessages.COR_NAO_INFORMADA);
        } else if (item.getBase() == null) {
            throw new ConflictException(Messages.EquipeMessages.BASE_NAO_INFORMADA);
        } else if (item.getImagem() == null) {
            throw new ConflictException(Messages.EquipeMessages.IMAGEM_NAO_INFORMADA);
        }
    }

    public void remove(Long id) throws ConflictException, NotFoundException {

        Equipe item = equipeDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.EquipeMessages.EQUIPE_NAO_ENCONTRADA);
        }

        equipeDao.delete(item);
    }

    public void seedData() {
        List<Equipe> listaEquipes = Constants.NomesEquipes();

        for (Equipe equipe : listaEquipes) {
            Query.Filter f1 = new Query.FilterPredicate("nome", Query.FilterOperator.EQUAL, equipe.getNome());
            Query.Filter f2 = new Query.FilterPredicate("cor", Query.FilterOperator.EQUAL, equipe.getCor());
            Query.Filter f3 = new Query.FilterPredicate("base", Query.FilterOperator.EQUAL, equipe.getBase());
            Query.Filter f4 = Query.CompositeFilterOperator.and(f1, f2);
            Query.Filter filter = Query.CompositeFilterOperator.and(f4, f3);

            Equipe eqp = equipeDao.getByFilter(filter);

            if (eqp == null) {
                equipeDao.insert(equipe);
            }

        }
    }
}
