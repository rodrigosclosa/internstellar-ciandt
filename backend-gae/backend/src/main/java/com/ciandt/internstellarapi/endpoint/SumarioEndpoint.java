package com.ciandt.internstellarapi.endpoint;

import com.ciandt.internstellarapi.entity.GrupoSumarioAvaliacao;
import com.ciandt.internstellarapi.service.GrupoSumarioService;
import com.ciandt.internstellarapi.service.TokenService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

import javax.inject.Named;

/**
 * Created by helder on 13/10/16.
 */

@Api(
        name = "sumarios",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.internstellarapi.ciandt.com",
                ownerName = "endpoint.internstellarapi.ciandt.com",
                packagePath = ""
        )
)
public class SumarioEndpoint {

    private GrupoSumarioService grupoSumarioService;
    private TokenService tokenService;

    public SumarioEndpoint() {
        grupoSumarioService = new GrupoSumarioService();
        tokenService = new TokenService();
    }

    @ApiMethod(name = "sumarioGrupo", path = "getSumario", httpMethod = ApiMethod.HttpMethod.GET)
    public List<GrupoSumarioAvaliacao> sumarioGrupos(@Named("token") String token,
                                                     @Named("base") String base) throws UnauthorizedException {
        tokenService.validarTokenAdministrador(token);
        List<GrupoSumarioAvaliacao> sumariosPorBase;
        sumariosPorBase = grupoSumarioService.getSumarioGruposPorBase(base);
        return sumariosPorBase;
    }
}
