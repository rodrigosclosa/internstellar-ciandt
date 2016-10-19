package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.PerguntaGrupoDao;
import com.ciandt.internstellarapi.entity.Pergunta;
import com.ciandt.internstellarapi.entity.PerguntaGrupo;
import com.ciandt.internstellarapi.helper.Messages;
import com.google.api.server.spi.response.BadRequestException;
import com.google.appengine.api.datastore.Query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by helder on 18/10/16.
 */

public class PerguntaGrupoService {

    private static final int FIRST_LIST_ITEM = 0;

    private static final int IS_EQUAL = 0;

    private PerguntaGrupoDao perguntaGrupoDao;

    public PerguntaGrupoService() {
        perguntaGrupoDao = new PerguntaGrupoDao();
    }

    public PerguntaGrupo findByPlanetaGrupoPergunta(Long idPlaneta, Long idGrupo, Long idPergunta) {
        Query.Filter filterPlaneta = new Query.FilterPredicate("idPlaneta", Query.FilterOperator.EQUAL, idPlaneta);

        Query.Filter filterGrupo = new Query.FilterPredicate("idGrupo", Query.FilterOperator.EQUAL, idGrupo);

        Query.Filter filterPergunta = new Query.FilterPredicate("idPergunta", Query.FilterOperator.EQUAL, idPergunta);

        Query.Filter filterPLanetaGrupo = Query.CompositeFilterOperator.and(filterPlaneta, filterGrupo);
        Query.Filter filter = Query.CompositeFilterOperator.and(filterPLanetaGrupo, filterPergunta);

        List<PerguntaGrupo> perguntasGrupo = perguntaGrupoDao.listByFilter(filter);
        if (perguntasGrupo != null && !perguntasGrupo.isEmpty()) {
            Collections.sort(perguntasGrupo, new ComparatorPerguntaGrupo());
        } else {
            return null;
        }
        return perguntasGrupo.get(FIRST_LIST_ITEM);
    }


    public List<PerguntaGrupo> findByPlanetaGrupo(Long idPlaneta, Long idGrupo) throws BadRequestException {
        Query.Filter filterPlaneta = new Query.FilterPredicate("idPlaneta", Query.FilterOperator.EQUAL, idPlaneta);

        Query.Filter filterGrupo = new Query.FilterPredicate("idGrupo", Query.FilterOperator.EQUAL, idGrupo);

        Query.Filter filter = Query.CompositeFilterOperator.and(filterPlaneta, filterGrupo);
        List<PerguntaGrupo> perguntasGrupo = perguntaGrupoDao.listByFilter(filter);
        if (perguntasGrupo != null && !perguntasGrupo.isEmpty()) {
            Collections.sort(perguntasGrupo, new ComparatorPerguntaGrupo());
        } else {
            return null;
        }
        return perguntasGrupo;
    }

    public void update(PerguntaGrupo entity){
        perguntaGrupoDao.update(entity);
    }

    public void salvar(PerguntaGrupo entity){
        perguntaGrupoDao.insert(entity);
    }

    private class ComparatorPerguntaGrupo implements Comparator<PerguntaGrupo> {
        @Override
        public int compare(PerguntaGrupo perguntaGrupo, PerguntaGrupo t1) {
            int tentativasCompare = perguntaGrupo.getQuantidadeTentativas().compareTo(t1.getQuantidadeTentativas());
            if (tentativasCompare == IS_EQUAL) {
                return perguntaGrupo.getIdPergunta().compareTo(t1.getIdPergunta());
            }
            return tentativasCompare;
        }
    }
}
