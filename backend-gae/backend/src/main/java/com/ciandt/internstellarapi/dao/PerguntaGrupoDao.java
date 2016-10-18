package com.ciandt.internstellarapi.dao;

import com.ciandt.internstellarapi.entity.Equipe;
import com.ciandt.internstellarapi.entity.PerguntaGrupo;
import com.google.appengine.api.datastore.Query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by helder on 17/10/16.
 */

public class PerguntaGrupoDao extends GenericDao<PerguntaGrupo> {

    private int FIRST_LIST_ITEM = 0;

    public PerguntaGrupo findByPlanetaGrupo(Long idPlaneta, Long idGrupo) {
        Query.Filter filterPlaneta = new Query.FilterPredicate("idPlaneta", Query.FilterOperator.EQUAL, idPlaneta);

        Query.Filter filterGrupo = new Query.FilterPredicate("idGrupo", Query.FilterOperator.EQUAL, idGrupo);

        Query.Filter filter = Query.CompositeFilterOperator.and(filterPlaneta, filterGrupo);
        List<PerguntaGrupo> perguntasGrupo = listByFilter(filter);
        if (perguntasGrupo != null && !perguntasGrupo.isEmpty()) {
            Collections.sort(perguntasGrupo, new ComparatorPerguntaGrupo());
        } else {
            return null;
        }
        return perguntasGrupo.get(FIRST_LIST_ITEM);
    }

    public PerguntaGrupo findByPlanetaGrupoPergunta(Long idPlaneta, Long idGrupo, Long idPergunta) {
        Query.Filter filterPlaneta = new Query.FilterPredicate("idPlaneta", Query.FilterOperator.EQUAL, idPlaneta);

        Query.Filter filterGrupo = new Query.FilterPredicate("idGrupo", Query.FilterOperator.EQUAL, idGrupo);

        Query.Filter filterPergunta = new Query.FilterPredicate("idPergunta", Query.FilterOperator.EQUAL, idPergunta);

        Query.Filter filterPLanetaGrupo = Query.CompositeFilterOperator.and(filterPlaneta, filterGrupo);
        Query.Filter filter = Query.CompositeFilterOperator.and(filterPLanetaGrupo, filterPergunta);

        List<PerguntaGrupo> perguntasGrupo = listByFilter(filter);
        if (perguntasGrupo != null && !perguntasGrupo.isEmpty()) {
            Collections.sort(perguntasGrupo, new ComparatorPerguntaGrupo());
        } else {
            return null;
        }
        return perguntasGrupo.get(FIRST_LIST_ITEM);
    }

    private class ComparatorPerguntaGrupo implements Comparator<PerguntaGrupo> {

        @Override
        public int compare(PerguntaGrupo perguntaGrupo, PerguntaGrupo t1) {
            return perguntaGrupo.getQuantidadeTentativas().compareTo(t1.getQuantidadeTentativas());
        }
    }
}
