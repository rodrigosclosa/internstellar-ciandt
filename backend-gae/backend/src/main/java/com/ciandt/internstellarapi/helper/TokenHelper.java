package com.ciandt.internstellarapi.helper;

import com.ciandt.internstellarapi.entity.Tokens;
import com.ciandt.internstellarapi.service.TokensService;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.security.SecureRandom;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class TokenHelper {
    private static TokensService tokensService;
    private static TokenHelper ourInstance = new TokenHelper();

    public static TokenHelper getInstance() {
        return ourInstance;
    }

    private TokenHelper() {
        tokensService = new TokensService();
    }

    public static String NewToken() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    public static Boolean TokenValido(String token) throws UnauthorizedException {
        Boolean retorno = false;

        if(token == null || token.isEmpty()) {
            throw new UnauthorizedException("Token de autorização não informado.");
        }

        Tokens item = tokensService.getByToken(token);
        retorno = true;

        return retorno;
    }
}
