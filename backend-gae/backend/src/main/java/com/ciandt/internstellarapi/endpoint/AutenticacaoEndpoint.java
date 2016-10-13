package com.ciandt.internstellarapi.endpoint;

/**
 * Created by helder on 10/10/16.
 */

import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.AutenticacaoService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

import javax.inject.Named;

/**
 * Created by falcao on 06/10/16.
 */

@Api(
        name = "auth",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.internstellarapi.ciandt.com",
                ownerName = "endpoint.internstellarapi.ciandt.com",
                packagePath = ""
        )
)
public class AutenticacaoEndpoint {

    private AutenticacaoService autenticacaoService;

    public AutenticacaoEndpoint() {
        autenticacaoService = new AutenticacaoService();
    }

    @ApiMethod(name = "autenticacao", path = "get", httpMethod = ApiMethod.HttpMethod.GET)
    public Grupo getGrupos(@Named("idGrupo") Long idGrupo,
                           @Named("senha") String senha) throws NotFoundException,
            UnauthorizedException,
            InternalServerErrorException {
        Grupo grupo;
        grupo = autenticacaoService.autenticarGrupo(idGrupo, senha);
        return grupo;
    }
}
