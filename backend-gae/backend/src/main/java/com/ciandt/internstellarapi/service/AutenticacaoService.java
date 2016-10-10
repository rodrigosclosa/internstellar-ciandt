package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Token;
import com.ciandt.internstellarapi.helper.AuthHelper;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.helper.TokenHelper;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.security.NoSuchAlgorithmException;

/**
 * Created by helder on 10/10/16.
 */

public class AutenticacaoService {

    private GrupoService grupoService;
    private TokenService tokenService;

    public AutenticacaoService() {
        grupoService = new GrupoService();
        tokenService = new TokenService();
    }

    public Grupo autenticarGrupo(Long idGrupo, String senha) throws UnauthorizedException, InternalServerErrorException {
        Grupo grupo = null;
        try {
            String hashSenha = AuthHelper.generateHashFromText(senha);
            grupo = grupoService.getById(idGrupo);
            if (!grupo.getSenha().equals(hashSenha)) {
                throw new BadRequestException(Messages.AutenticacaoMessages.AUTENTICACAO_INVALIDA);
            }
            Token token = gerarToken(grupo);
            grupo.setToken(token.getToken());
        } catch (NotFoundException | BadRequestException e) {
            throw new UnauthorizedException(Messages.AutenticacaoMessages.AUTENTICACAO_INVALIDA);
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerErrorException(String.format(Messages.GenericMessages.ERRO_NAO_ESPERADO, e.getMessage()));
        }
        return grupo;
    }

    private Token gerarToken(Grupo grupo) throws NotFoundException, BadRequestException {
        tokenService.removeByGrupo(grupo.getId());
        Token token = new Token();
        token.setIdGrupo(grupo.getId());
        token.setToken(TokenHelper.NewToken());
        token = tokenService.insert(token);
        return token;
    }
}
