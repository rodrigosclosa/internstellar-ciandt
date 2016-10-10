package com.ciandt.internstellarapi.helper;

import com.ciandt.internstellarapi.entity.Token;
import com.ciandt.internstellarapi.service.TokenService;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.UUID;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class TokenHelper {
    private static TokenService tokensService;
    private static TokenHelper ourInstance = new TokenHelper();

    public static TokenHelper getInstance() {
        return ourInstance;
    }

    private TokenHelper() {
        tokensService = new TokenService();
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

        Token item = tokensService.getByToken(token);
        retorno = true;

        return retorno;
    }
}
