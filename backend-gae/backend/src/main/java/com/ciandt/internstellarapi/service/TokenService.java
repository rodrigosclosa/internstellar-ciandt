package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.TokensDao;
import com.ciandt.internstellarapi.entity.Token;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.util.Constants;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class TokenService {

    private TokensDao tokensDao;

    public TokenService() {
        tokensDao = new TokensDao();
    }

    public List<Token> list() {
        return tokensDao.listAll();
    }

    public Token getByToken(String token) throws UnauthorizedException {
        if (Constants.ADM_TOKEN.equals(token)) {
            return tokenAdm();
        }
        Token item = tokensDao.getByProperty("token", token);

        if (item == null) {
            throw new UnauthorizedException(Messages.TokenMessages.ACESSO_NEGADO_TOKEN_NAO_ENCONTRADO_INVALIDO);
        }

        return item;
    }

    public Token insert(Token item) throws BadRequestException {
        validarToken(item);

        tokensDao.insert(item);

        return item;
    }

    public Token update(Token item) throws NotFoundException, BadRequestException {
        validarToken(item);

        Token u = tokensDao.getById(item.getId());

        if (u == null) {
            throw new NotFoundException(Messages.TokenMessages.TOKEN_NAO_ENCONTRADO);
        }

        tokensDao.update(item);

        return item;
    }

    private void validarToken(Token token) throws BadRequestException {
        if (token == null) {
            throw new BadRequestException(Messages.TokenMessages.ITEM_NAO_INFORMADO);
        } else if (token.getToken() == null) {
            throw new BadRequestException(Messages.TokenMessages.TOKEN_NAO_INFORMADO);
        }
    }

    public void remove(long id) throws ConflictException, NotFoundException {
        Token item = tokensDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.TokenMessages.TOKEN_NAO_ENCONTRADO);
        }

        tokensDao.delete(item);
    }

    public void removeByGrupo(Long idGrupo) {
        Token token;
        token = tokensDao.getByProperty("idGrupo", idGrupo);
        if (token != null) {
            tokensDao.delete(token);
        }
    }

    public void validarTokenAdministrador(String token) throws UnauthorizedException {
        if (!Constants.ADM_TOKEN.equals(token)) {
            throw new UnauthorizedException(Messages.TokenMessages.ACESSO_NEGADO_TOKEN_NAO_ENCONTRADO_INVALIDO);
        }
    }

    private Token tokenAdm() {
        Token token = new Token();
        token.setToken(Constants.ADM_TOKEN);
        return token;
    }

}
