package com.ciandt.internstellarapi.endpoint;


import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.service.PerguntaService;
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

    public PerguntaEndpoint() {
        perguntaService = new PerguntaService();
    }


    @ApiMethod(name = "getPerguntas", path = "get", httpMethod = "GET")
    public List<Pergunta> getPerguntas(@Nullable @Named("idPLaneta") Long idPlaneta) throws NotFoundException {
        if (idPlaneta == null) {
            return perguntaService.list();
        } else {
            return perguntaService.findByPlaneta(idPlaneta);
        }
    }

    @ApiMethod(name = "insertPergunta", path = "new", httpMethod = ApiMethod.HttpMethod.POST)
    public Pergunta insertPergunta(Pergunta item) throws BadRequestException {
        return perguntaService.insert(item);
    }
}
