package com.ciandt.internstellarapi.endpoint;

import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Planeta;
import com.ciandt.internstellarapi.service.PlanetaService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;

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

    public PlanetaEndpoint() {
        planetaService = new PlanetaService();
    }

    @ApiMethod(name = "getPlanetas", path = "get", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Planeta> getPlanetas(@Nullable @Named("nome") String nome) throws NotFoundException {
        if (nome == null) {
            return planetaService.list();
        } else {
            return planetaService.list(nome);
        }
    }

    @ApiMethod(name = "insertPlaneta", path = "new", httpMethod = ApiMethod.HttpMethod.POST)
    public Planeta insertGrupo(Planeta item) throws BadRequestException {
        return planetaService.insert(item);
    }
}
