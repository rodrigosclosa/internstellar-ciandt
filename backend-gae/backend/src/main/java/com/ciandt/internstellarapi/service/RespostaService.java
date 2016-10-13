package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.GrupoDao;
import com.ciandt.internstellarapi.dao.PerguntaDao;
import com.ciandt.internstellarapi.dao.RespostaDao;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.entity.PerguntaOpcao;
import com.ciandt.internstellarapi.entity.Resposta;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.validator.RespostaValidator;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

import java.util.List;

/**
 * Created by helder on 10/10/16.
 */

public class RespostaService {

    private RespostaDao respostaDao;
    private GrupoDao grupoDao;
    private PerguntaDao perguntaDao;

    private RespostaValidator respostaValidator;

    public RespostaService() {
        respostaDao = new RespostaDao();
        respostaValidator = new RespostaValidator();
        grupoDao = new GrupoDao();
        perguntaDao = new PerguntaDao();
    }

    public List<Resposta> list() {
        List<Resposta> retorno = respostaDao.listAll();

        for (Resposta resp : retorno) {
            Pergunta per = perguntaDao.getByKey(resp.getIdPergunta());

            if (per != null) {
                resp.setPergunta(per);
            }

            Grupo gr = grupoDao.getByKey(resp.getIdGrupo());

            if (gr != null) {
                resp.setGrupo(gr);
            }
        }

        return retorno;
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

    public List<Resposta> findByGrupo(Long idGrupo) {
        List<Resposta> result;
        result = respostaDao.listByProperty("idGrupo", idGrupo);
        return result;
    }

    public Integer countRespostasCorretas(List<Resposta> respostas) {
        Integer count = 0;
        for (Resposta resposta : respostas) {
            String opcaoCorreta = getOpcaoCorreta(resposta.getPergunta());
            if (opcaoCorreta.equals(resposta.getIdResposta())) {
                count++;
            }
        }
        return count;
    }

    private String getOpcaoCorreta(Pergunta pergunta) {
        String idOpcao = null;
        for (PerguntaOpcao opcao : pergunta.getOpcoes()) {
            if (opcao.getCorreta()) {
                idOpcao = opcao.getIdOpcao();
                break;
            }
        }
        return idOpcao;
    }


    private boolean respostaJaEnviada(Resposta respostaInformada) throws BadRequestException {
        Query.Filter filterGrupo = new Query.FilterPredicate("idGrupo", Query.FilterOperator.EQUAL, respostaInformada.getIdGrupo());
        Query.Filter filterPergunta = new Query.FilterPredicate("idPergunta", Query.FilterOperator.EQUAL, respostaInformada.getIdPergunta());
        Query.Filter filter = Query.CompositeFilterOperator.and(filterGrupo, filterPergunta);
        Resposta resposta = respostaDao.getByFilter(filter);
        return resposta != null;
    }

}
