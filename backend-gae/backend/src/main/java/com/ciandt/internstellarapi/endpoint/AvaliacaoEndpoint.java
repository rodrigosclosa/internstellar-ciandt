package com.ciandt.internstellarapi.endpoint;

import com.ciandt.internstellarapi.entity.Avaliacao;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.service.AvaliacaoService;
import com.ciandt.internstellarapi.service.TokenService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

import javax.inject.Named;

/**
 * Created by helder on 11/10/16.
 */
@Api(
        name = "avaliacoes",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.internstellarapi.ciandt.com",
                ownerName = "endpoint.internstellarapi.ciandt.com",
                packagePath = ""
        )
)
public class AvaliacaoEndpoint {

    private AvaliacaoService avaliacaoService;

    private TokenService tokenService;

    public AvaliacaoEndpoint() {
        avaliacaoService = new AvaliacaoService();
        tokenService = new TokenService();
    }

    @ApiMethod(name = "getAvaliacao", path = "get", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Avaliacao> getAvaliacoes(@Named("idGrupo") Long idGrupo,
                                         @Named("token") String token)
            throws NotFoundException, UnauthorizedException {
        tokenService.validarTokenAdministrador(token);
        if (idGrupo == null) {
            return avaliacaoService.list();
        }
        return avaliacaoService.findByGrupo(idGrupo);
    }

    @ApiMethod(name = "insertAvaliacao", path = "new", httpMethod = ApiMethod.HttpMethod.POST)
    public Avaliacao insertAvaliacao(Avaliacao item, @Named("token") String token)
            throws BadRequestException, UnauthorizedException {
        tokenService.validarTokenAdministrador(token);
        Avaliacao avaliacao;
        avaliacao = avaliacaoService.insert(item);
        return avaliacao;
    }

    @ApiMethod(name = "updateAvaliacao", path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
    public Avaliacao updateAvaliacao(@Named("token") String token, Avaliacao item)
            throws NotFoundException, BadRequestException, UnauthorizedException {
        tokenService.validarTokenAdministrador(token);
        return avaliacaoService.update(item);
    }

    @ApiMethod(name = "removeAvaliacao", path = "delete/{id}", httpMethod = ApiMethod.HttpMethod.DELETE)
    public void removeAvaliacao(@Named("id") Long id, @Named("token") String token)
            throws NotFoundException, UnauthorizedException {
        tokenService.validarTokenAdministrador(token);
        avaliacaoService.remove(id);
    }


}
