package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.TokensDao;
import com.ciandt.internstellarapi.entity.Tokens;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class TokensService {

    private TokensDao tokensDao;

    public TokensService() {
        tokensDao = new TokensDao();
    }

    public List<Tokens> list() {
        return tokensDao.listAll();
    }

    public List<Tokens> list(String codigo) throws NotFoundException {

        List<Tokens> list = tokensDao.listByProperty("token", codigo);

        if(list == null || list.size() < 1) {
            throw new NotFoundException(String.format("Não foram encontrados dados para o código: %s", codigo));
        }

        return list;
    }

    public Tokens getByCodigo(String codigo) throws NotFoundException {
        Tokens item = tokensDao.getByProperty("codigo", codigo);

        if(item == null) {
            throw new NotFoundException("Token não encontrado.");
        }

        return item;
    }

    public Tokens getByToken(String token) throws UnauthorizedException {
        Tokens item = tokensDao.getByProperty("token", token);

        if(item == null) {
            throw new UnauthorizedException("Acesso negado. Token não encontrado ou inválido.");
        }

        return item;
    }

    public Tokens insert(Tokens item) throws ConflictException, NotFoundException {
        if(item == null)
        {
            throw new ConflictException("Item não informado.");
        }
        else if(item.getToken() == null)
        {
            throw new ConflictException("Token não informado.");
        }

        tokensDao.insert(item);

        return item;
    }

    public Tokens update(Tokens item) throws ConflictException, NotFoundException {
        if(item == null)
        {
            throw new ConflictException("Item não informado.");
        }
        else if(item.getToken() == null)
        {
            throw new ConflictException("Token não informado.");
        }

        Tokens u = tokensDao.getById(item.getId());

        if(u == null) {
            throw new NotFoundException("Token não encontrado");
        }

        tokensDao.update(item);

        return item;
    }

    public void remove(long id) throws ConflictException, NotFoundException {
        Tokens item = tokensDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException("Token não encontrado.");
        }

        tokensDao.delete(item);
    }
}
