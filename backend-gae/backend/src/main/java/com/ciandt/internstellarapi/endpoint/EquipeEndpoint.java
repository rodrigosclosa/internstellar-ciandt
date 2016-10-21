package com.ciandt.internstellarapi.endpoint;

import com.ciandt.internstellarapi.entity.Equipe;
import com.ciandt.internstellarapi.service.EquipeService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by rodrigosclosa on 29/08/16.
 */
@Api(
        name = "equipes",
        version = "v1",
        namespace = @ApiNamespace(
            ownerDomain = "endpoint.internstellarapi.ciandt.com",
            ownerName = "endpoint.internstellarapi.ciandt.com",
            packagePath = ""
        )
)
public class EquipeEndpoint {

    private EquipeService equipeService;

    public EquipeEndpoint() {
        equipeService = new EquipeService();
    }

    @ApiMethod(name = "getEquipes", path = "get", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Equipe> getEquipes(@Nullable @Named("nome") String nome) throws NotFoundException {
        if(nome == null)
            return equipeService.list();
        else
            return equipeService.list(nome);
    }

    @ApiMethod(name = "getEquipe", path = "get/{id}", httpMethod = ApiMethod.HttpMethod.GET)
    public Equipe getEquipe(@Named("id") Long id) throws NotFoundException {
        return equipeService.getById(id);
    }

//    @ApiMethod(name = "seedData", path = "seed", httpMethod = ApiMethod.HttpMethod.POST)
//    public void seedData() throws NotFoundException, ConflictException {
//        equipeService.seedData();
//    }
}
