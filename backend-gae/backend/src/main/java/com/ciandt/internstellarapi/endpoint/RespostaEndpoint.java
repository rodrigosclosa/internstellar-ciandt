package com.ciandt.internstellarapi.endpoint;

import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Resposta;
import com.ciandt.internstellarapi.service.RespostaService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.UnauthorizedException;

/**
 * Created by falcao on 06/10/16.
 */

@Api(
        name = "respostas",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.internstellarapi.ciandt.com",
                ownerName = "endpoint.internstellarapi.ciandt.com",
                packagePath = ""
        )
)
public class RespostaEndpoint {

    private RespostaService respostaService;

    public RespostaEndpoint() {
        respostaService = new RespostaService();
    }

    @ApiMethod(name = "insertResposta", path = "new", httpMethod = ApiMethod.HttpMethod.POST)
    public Resposta insertResposta(Resposta item) throws BadRequestException, UnauthorizedException {
        return respostaService.insert(item);
    }
}
