package com.ciandt.internstellarapi.endpoint;


import com.ciandt.internstellarapi.entity.Avaliacao;
import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.service.PerguntaService;
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
 * Created by falcao on 06/10/16.
 */

@Api(
        name = "perguntas",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.internstellarapi.ciandt.com",
                ownerName = "endpoint.internstellarapi.ciandt.com",
                packagePath = ""
        )
)
public class PerguntaEndpoint {

    private PerguntaService perguntaService;

    private TokenService tokenService;

    public PerguntaEndpoint() {
        perguntaService = new PerguntaService();
        tokenService = new TokenService();
    }


    @ApiMethod(name = "getPerguntas", path = "get", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Pergunta> getPerguntas(@Nullable @Named("idPLaneta") Long idPlaneta,
                                       @Named("token") String token) throws NotFoundException, UnauthorizedException {
        tokenService.getByToken(token);
        if (idPlaneta == null) {
            return perguntaService.list();
        } else {
            return perguntaService.findByPlaneta(idPlaneta);
        }
    }

    @ApiMethod(name = "updatePergunta", path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
    public Pergunta updatePergunta(@Named("tokenAdm") String token, Pergunta pergunta)
            throws UnauthorizedException, BadRequestException, NotFoundException {
        tokenService.validarTokenAdministrador(token);
        return perguntaService.update(pergunta);
    }

    @ApiMethod(name = "insertPergunta", path = "new", httpMethod = ApiMethod.HttpMethod.POST)
    public Pergunta insertPergunta(@Named("tokenAdm") String token, Pergunta item)
            throws BadRequestException, UnauthorizedException {
        tokenService.validarTokenAdministrador(token);
        return perguntaService.insert(item);
    }
}
