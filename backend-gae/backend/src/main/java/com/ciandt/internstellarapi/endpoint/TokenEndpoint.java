package com.ciandt.internstellarapi.endpoint;

import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Token;
import com.ciandt.internstellarapi.service.TokenService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

import javax.inject.Named;

/**
 * Created by helder on 10/10/16.
 */
@Api(
        name = "tokens",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.internstellarapi.ciandt.com",
                ownerName = "endpoint.internstellarapi.ciandt.com",
                packagePath = ""
        )
)
//TODO: Endpoint deve ser protegido
public class TokenEndpoint {

    private TokenService tokenService;

    public TokenEndpoint() {
        tokenService = new TokenService();
    }

    @ApiMethod(name = "getTokens", path = "get", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Token> getTokens(@Named("token") String token) throws NotFoundException, UnauthorizedException {
        tokenService.validarTokenAdministrador(token);
        return tokenService.list();
    }
}
