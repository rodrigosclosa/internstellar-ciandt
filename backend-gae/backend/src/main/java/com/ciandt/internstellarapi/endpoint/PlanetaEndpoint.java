package com.ciandt.internstellarapi.endpoint;

import com.ciandt.internstellarapi.entity.Planeta;
import com.ciandt.internstellarapi.service.PlanetaService;
import com.ciandt.internstellarapi.service.TokenService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.Collections;
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
    public List<Planeta> getPlanetas(@Nullable @Named("id") Long id,
                                     @Named("token") String token)
            throws NotFoundException, UnauthorizedException {
        tokenService.getByToken(token);
        if (id == null) {
            return planetaService.list();
        } else {
            return Collections.singletonList(planetaService.getById(id));
        }
    }
}
