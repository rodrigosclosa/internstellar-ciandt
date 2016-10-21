package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.GrupoDao;
import com.ciandt.internstellarapi.dao.PerguntaDao;
import com.ciandt.internstellarapi.dao.PerguntaGrupoDao;
import com.ciandt.internstellarapi.dao.RespostaDao;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.entity.PerguntaGrupo;
import com.ciandt.internstellarapi.entity.PerguntaOpcao;
import com.ciandt.internstellarapi.entity.Resposta;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.validator.RespostaValidator;
import com.ciandt.internstellarapi.util.DataControlHelper;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.InternalServerErrorException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helder on 10/10/16.
 */

public class RespostaService {

    private RespostaDao respostaDao;
    private GrupoDao grupoDao;
    private PerguntaDao perguntaDao;
    private PerguntaGrupoService perguntaGrupoService;

    private RespostaValidator respostaValidator;

    public RespostaService() {
        respostaDao = new RespostaDao();
        respostaValidator = new RespostaValidator();
        grupoDao = new GrupoDao();
        perguntaDao = new PerguntaDao();
        perguntaGrupoService = new PerguntaGrupoService();
    }

    public List<Resposta> list() {
        List<Resposta> retorno = respostaDao.listAll();

        fetchPergunta(retorno);

        return retorno;
    }

    private void fetchPergunta(List<Resposta> retorno) {
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
    }

    public Resposta insert(Resposta resposta) throws UnauthorizedException, BadRequestException, InternalServerErrorException {
        respostaValidator.validar(resposta);
        if (respostaJaEnviada(resposta)) {
            throw new BadRequestException(Messages.RespostaMessages.RESPOSTA_JA_ENVIADA);
        }
        Pergunta pergunta = perguntaDao.getByKey(resposta.getIdPergunta());
        DataControlHelper.PreencherDataComHoraAtual(resposta);
        PerguntaGrupo perguntaGrupo = perguntaGrupoService.findByPlanetaGrupoPergunta(pergunta.getPlanetaId(),
                resposta.getIdGrupo(),
                resposta.getIdPergunta());
        if (perguntaGrupo == null) {
            throw new InternalServerErrorException(Messages.RespostaMessages.PROXIMA_PERGUNTA_NAO_INFORMADA);
        }
        perguntaGrupo.setRespondida(Boolean.TRUE);
        perguntaGrupoService.salvar(perguntaGrupo);
        respostaDao.save(resposta);

        return resposta;
    }

    public List<Resposta> findByGrupo(Long idGrupo) {
        List<Resposta> result;
        result = respostaDao.listByProperty("idGrupo", idGrupo);
        fetchPergunta(result);
        return result;
    }

    public List<Resposta> respostasCorretas(List<Resposta> respostas) {
        List<Resposta> respostasCorretas = new ArrayList<>();
        for (Resposta resposta : respostas) {
            String opcaoCorreta = getOpcaoCorreta(resposta.getPergunta());
            if (opcaoCorreta.equals(resposta.getIdResposta())) {
                respostasCorretas.add(resposta);
            }
        }
        return respostasCorretas;
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
