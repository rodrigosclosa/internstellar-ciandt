package com.ciandt.internstellarapi.endpoint;

import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.service.GrupoService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;

import java.util.List;

import javax.inject.Named;

/**
 * Created by falcao on 06/10/16.
 */

@Api(
        name = "grupos",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.internstellarapi.ciandt.com",
                ownerName = "endpoint.internstellarapi.ciandt.com",
                packagePath = ""
        )
)
public class GrupoEndpoint {

    private GrupoService grupoService;

    public GrupoEndpoint() {
        grupoService = new GrupoService();
    }

    @ApiMethod(name = "getGrupos", path = "get", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Grupo> getGrupos(@Nullable @Named("nome") String nome) throws NotFoundException {
        if (nome == null) {
            return grupoService.list();
        } else {
            return grupoService.findByName(nome);
        }
    }

    @ApiMethod(name = "getGrupo", path = "get/{id}", httpMethod = ApiMethod.HttpMethod.GET)
    public Grupo getGrupo(@Named("id") Long id) throws NotFoundException {
        return grupoService.getById(id);
    }

    @ApiMethod(name = "insertGrupo", path = "new", httpMethod = ApiMethod.HttpMethod.POST)
    public Grupo insertGrupo(Grupo item) throws BadRequestException {
        return grupoService.insert(item);
    }
}
