package com.ciandt.internstellarapi.endpoint;

import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.entity.Planeta;
import com.ciandt.internstellarapi.service.PlanetaService;
import com.ciandt.internstellarapi.service.TokenService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

import javax.inject.Named;

/**
 * Created by helder on 07/10/16.
 */

@Api(
        name = "planetas",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.internstellarapi.ciandt.com",
                ownerName = "endpoint.internstellarapi.ciandt.com",
                packagePath = ""
        )
)
public class PlanetaEndpoint {

    private PlanetaService planetaService;

    private TokenService tokenService;

    public PlanetaEndpoint() {
        planetaService = new PlanetaService();
        tokenService = new TokenService();
    }

    @ApiMethod(name = "getPlanetas", path = "get", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Planeta> getPlanetas(@Nullable @Named("nome") String nome,
                                     @Named("token") String token)
            throws NotFoundException, UnauthorizedException {
        tokenService.getByToken(token);
        if (nome == null) {
            return planetaService.list();
        } else {
            return planetaService.list(nome);
        }
    }


    @ApiMethod(name = "updatePlaneta", path = "update", httpMethod = ApiMethod.HttpMethod.POST)
    public Planeta updatePlaneta(Planeta planeta, @Named("tokenAdm") String token)
            throws UnauthorizedException, BadRequestException, NotFoundException {
        tokenService.validarTokenAdministrador(token);
        return planetaService.update(planeta);
    }

    @ApiMethod(name = "insertPlaneta", path = "new", httpMethod = ApiMethod.HttpMethod.POST)
    public Planeta insertPlaneta(@Named("tokenAdm") String token, Planeta item) throws BadRequestException, UnauthorizedException {
        tokenService.validarTokenAdministrador(token);
        return planetaService.insert(item);
    }
}
