package com.ciandt.internstellarapi.dao;

import com.ciandt.internstellarapi.entity.Avaliacao;
import com.google.appengine.api.datastore.Query;

/**
 * Created by helder on 11/10/16.
 */

public class AvaliacaoDao extends GenericDao<Avaliacao> {

    public Avaliacao findByDesafioAndGrupo(Integer desafio, Long idGrupo) {
        Query.Filter filterDesafio = new Query.FilterPredicate("desafio",
                Query.FilterOperator.EQUAL, desafio);

        Query.Filter filterGrupo = new Query.FilterPredicate("idGrupo",
                Query.FilterOperator.EQUAL, idGrupo);

        Query.Filter filter = Query.CompositeFilterOperator.and(filterDesafio
                , filterGrupo);
        return getByFilter(filter);
    }
}
