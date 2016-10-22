package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.PerguntaDao;
import com.ciandt.internstellarapi.dao.PlanetaDao;
import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.entity.PerguntaGrupo;
import com.ciandt.internstellarapi.entity.PerguntaOpcao;
import com.ciandt.internstellarapi.entity.Planeta;
import com.ciandt.internstellarapi.helper.EntityHelper;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.validator.PerguntaValidator;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by helder on 07/10/16.
 */

public class PerguntaService {

    private static final int CODIGO_INICIAL_PERGUNTA = 1;
    private static final int FIRST_ITEM = 0;
    private static final int DE_PARA_INDEX_QUANTIDADE = 1;
    private static final int INCREMENTO_PADRAO_QUANTIDADE_TENTATIVAS = 1;
    private PerguntaDao perguntaDao;
    private PlanetaDao planetaDao;
    private PerguntaGrupoService perguntaGrupoService;

    private PerguntaValidator perguntaValidator;

    public PerguntaService() {
        perguntaDao = new PerguntaDao();
        perguntaValidator = new PerguntaValidator();
        planetaDao = new PlanetaDao();
        perguntaGrupoService = new PerguntaGrupoService();
    }

    public List<Pergunta> list() {
        List<Pergunta> result = perguntaDao.listAll();
        configurarPerguntasToPublic(result);

        fetchPlaneta(result);

        return result;
    }

    public Pergunta getById(Long id) throws NotFoundException {
        Pergunta item = null;

        item = perguntaDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.PerguntaMessages.PERGUNTA_NAO_ENCONTRADA);
        }
        configurarPerguntasToPublic(Collections.singletonList(item));

        Planeta pl = planetaDao.getByKey(item.getPlanetaId());

        if (pl != null) {
            item.setPlaneta(pl);
        }

        return item;
    }

    public List<Pergunta> findByPlaneta(Long idPlaneta) throws NotFoundException {
        List<Pergunta> perguntasResult = null;
        perguntasResult = perguntaDao.listByProperty("planetaId", idPlaneta);
        if (perguntasResult == null || perguntasResult.isEmpty()) {
            throw new NotFoundException(Messages.PerguntaMessages.NENHUMA_PERGUNTA_FOI_ENCONTRADA);
        }
        Iterator<Pergunta> perguntasIterator = perguntasResult.iterator();
        while (perguntasIterator.hasNext()) {
            configurarOpcoes(perguntasIterator.next().getOpcoes());
        }
        configurarPerguntasToPublic(perguntasResult);

        fetchPlaneta(perguntasResult);
        EntityHelper.orderEntities(perguntasResult);
        return perguntasResult;
    }

    private void fetchPlaneta(List<Pergunta> perguntasResult) {
        for (Pergunta P : perguntasResult) {
            Planeta pl = planetaDao.getByKey(P.getPlanetaId());

            if (pl != null) {
                P.setPlaneta(pl);
            }
        }
    }

    private void configurarPerguntasToPublic(List<Pergunta> perguntas) {
        removerResposta(perguntas);
    }

    private void removerResposta(List<Pergunta> perguntas) {
        Iterator<Pergunta> iteratorPergunta = perguntas.iterator();
        while (iteratorPergunta.hasNext()) {
            Iterator<PerguntaOpcao> opcoesIterator = iteratorPergunta.next().getOpcoes().iterator();
            while (opcoesIterator.hasNext()) {
                opcoesIterator.next().setCorreta(null);
            }
        }
    }

    private void configurarOpcoes(List<PerguntaOpcao> opcoes) {
        embaralharOpcoes(opcoes);
        configurarCodigoOpcoes(opcoes);
    }

    private void configurarCodigoOpcoes(List<PerguntaOpcao> opcoes) {
        Iterator<PerguntaOpcao> opcoesIterator = opcoes.iterator();
        int codigo = CODIGO_INICIAL_PERGUNTA;
        while (opcoesIterator.hasNext()) {
            opcoesIterator.next().setCodigo(codigo++);
        }
    }

    private void embaralharOpcoes(List<PerguntaOpcao> opcoes) {
        Collections.sort(opcoes, new RamdomOrder());
    }

    private class RamdomOrder implements Comparator<PerguntaOpcao> {

        @Override
        public int compare(PerguntaOpcao perguntaOpcao, PerguntaOpcao t1) {
            return Double.valueOf(Math.random()).compareTo(Math.random());
        }
    }


    public Pergunta insert(Pergunta item) throws BadRequestException {

        perguntaValidator.validar(item);
        configPergunta(item);
        perguntaDao.insert(item);

        return item;
    }

    public Pergunta update(Pergunta item) throws BadRequestException, NotFoundException {

        perguntaValidator.validar(item);
        Pergunta u = perguntaDao.getById(item.getId());
        if (u == null) {
            throw new NotFoundException(Messages.PerguntaMessages.PERGUNTA_NAO_ENCONTRADA);
        }
        configPergunta(item);
        perguntaDao.update(item);

        return item;
    }

    public Pergunta nextPergunta(Long idGrupo, Long idPlaneta) throws NotFoundException, BadRequestException {
        perguntaValidator.validarAcaoPergunta(idGrupo, idPlaneta);
        Pergunta perguntaToReturn = null;
        List<PerguntaGrupo> perguntasGrupo = perguntaGrupoService.findByPlanetaGrupo(idPlaneta, idGrupo);
        List<Pergunta> perguntasPlaneta = findByPlaneta(idPlaneta);
        if (perguntasGrupo == null) {
            perguntaToReturn = perguntasPlaneta.get(FIRST_ITEM);
            criarPerguntaGrupo(idGrupo, idPlaneta, perguntaToReturn.getId());
        } else {
            for (PerguntaGrupo perguntaGrupo : perguntasGrupo) {
                if (!perguntaGrupo.getRespondida()) {
                    perguntaToReturn = perguntaDao.getByKey(perguntaGrupo.getIdPergunta());
                    break;
                }
            }
            if (perguntaToReturn == null) {
                if (perguntasPlaneta.size() == perguntasGrupo.size()) {
                    //todas as perguntas respondidas
                    throw new BadRequestException(
                            Messages.PerguntaMessages.TODAS_AS_PERGUNTAS_DESTE_PLANETA_FORAM_RESPONDIDAS);
                } else {
                    perguntaToReturn = jumpPergunta(idGrupo, idPlaneta);
                }
            }
        }
        configurarPerguntasToPublic(Collections.singletonList(perguntaToReturn));
        return perguntaToReturn;
    }

    public Pergunta jumpPergunta(Long idGrupo, Long idPlaneta) throws NotFoundException, BadRequestException {
        perguntaValidator.validarAcaoPergunta(idGrupo, idPlaneta);
        List<PerguntaGrupo> perguntasGrupo = perguntaGrupoService.findByPlanetaGrupo(idPlaneta, idGrupo);
        List<PerguntaGrupo> perguntaGrupoNaoRespondida = new ArrayList<>();
        for (PerguntaGrupo pg : perguntasGrupo) {
            if (Boolean.FALSE.equals(pg.getRespondida())) {
                perguntaGrupoNaoRespondida.add(pg);
            }
        }
        if (perguntasGrupo == null || perguntasGrupo.isEmpty()) {
            throw new BadRequestException(Messages.PerguntaMessages.PRIMEIRA_PERGUNTA_NAO_REQUISITADA);
        }
        Pergunta perguntaResult;
        if (!perguntaGrupoNaoRespondida.isEmpty()) {
            PerguntaGrupo perguntaGrupo = perguntasGrupo.get(FIRST_ITEM);
            perguntaGrupo.setQuantidadeTentativas(perguntaGrupo.getQuantidadeTentativas()
                    + INCREMENTO_PADRAO_QUANTIDADE_TENTATIVAS);
            perguntaGrupoService.update(perguntaGrupo);

            Pergunta perguntaAtual = perguntaDao.getByKey(perguntaGrupo.getIdPergunta());

            List<Pergunta> perguntasPlaneta = findByPlaneta(idPlaneta);
            perguntaResult = selectNextPergunta(perguntaAtual, perguntasPlaneta);
            PerguntaGrupo perguntaGrupoJaExistente =
                    perguntaGrupoService.findByPlanetaGrupoPergunta(
                            idPlaneta, idGrupo, perguntaResult.getId());
            if (perguntaGrupoJaExistente == null) {
                criarPerguntaGrupo(idGrupo, idPlaneta, perguntaResult.getId());
            } else {
                perguntaGrupoJaExistente.setQuantidadeTentativas(
                        perguntaGrupoJaExistente.getQuantidadeTentativas()
                                + INCREMENTO_PADRAO_QUANTIDADE_TENTATIVAS);
            }
            configurarPerguntasToPublic(Collections.singletonList(perguntaResult));
        } else {
            perguntaResult = nextPergunta(idGrupo, idPlaneta);
        }
        return perguntaResult;
    }


    private Pergunta selectNextPergunta(Pergunta pergunta, List<Pergunta> perguntas) {
        Pergunta perguntaResult;
        int indexOf = perguntas.indexOf(pergunta);
        if ((indexOf + DE_PARA_INDEX_QUANTIDADE) == perguntas.size()) {
            perguntaResult = perguntas.get(FIRST_ITEM);
        } else {
            perguntaResult = perguntas.get(indexOf + DE_PARA_INDEX_QUANTIDADE);
        }
        return perguntaResult;
    }

    private PerguntaGrupo criarPerguntaGrupo(Long idGrupo, Long idPlaneta, Long idPergunta) {
        PerguntaGrupo perguntaGrupo = new PerguntaGrupo();
        perguntaGrupo.setIdGrupo(idGrupo);
        perguntaGrupo.setIdPlaneta(idPlaneta);
        perguntaGrupo.setIdPergunta(idPergunta);
        perguntaGrupo.setQuantidadeTentativas(0);
        perguntaGrupo.setRespondida(Boolean.FALSE);
        perguntaGrupoService.salvar(perguntaGrupo);
        return perguntaGrupo;
    }

    private void configPergunta(Pergunta pergunta) {
        for (PerguntaOpcao opcao : pergunta.getOpcoes()) {
            opcao.setIdOpcao(UUID.randomUUID().toString().replace("-", ""));
        }
    }

    public void remove(Long id) throws NotFoundException {

        Pergunta item = perguntaDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.PerguntaMessages.PERGUNTA_NAO_ENCONTRADA);
        }

        perguntaDao.delete(item);
    }

}
