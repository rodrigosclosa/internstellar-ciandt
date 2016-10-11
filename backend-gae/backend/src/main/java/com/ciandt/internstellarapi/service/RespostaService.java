package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.RespostaDao;
import com.ciandt.internstellarapi.entity.Resposta;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.validator.RespostaValidator;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

/**
 * Created by helder on 10/10/16.
 */

public class RespostaService {

    private RespostaDao respostaDao;

    private RespostaValidator respostaValidator;

    public RespostaService() {
        respostaDao = new RespostaDao();
        respostaValidator = new RespostaValidator();
    }

    public Resposta insert(Resposta resposta) throws UnauthorizedException, BadRequestException {
        respostaValidator.validar(resposta);
        if (respostaJaEnviada(resposta)) {
            throw new BadRequestException(Messages.RespostaMessages.RESPOSTA_JA_ENVIADA);

        } else {
            respostaDao.save(resposta);
        }
        return resposta;
    }

    private boolean respostaJaEnviada(Resposta respostaInformada) throws BadRequestException {
        Query.Filter filterGrupo = new Query.FilterPredicate("idGrupo", Query.FilterOperator.EQUAL, respostaInformada.getIdGrupo());
        Query.Filter filterPergunta = new Query.FilterPredicate("idPergunta", Query.FilterOperator.EQUAL, respostaInformada.getIdPergunta());
        Query.Filter filter = Query.CompositeFilterOperator.and(filterGrupo, filterPergunta);
        Resposta resposta = respostaDao.getByFilter(filter);
        return resposta != null;
    }

}
