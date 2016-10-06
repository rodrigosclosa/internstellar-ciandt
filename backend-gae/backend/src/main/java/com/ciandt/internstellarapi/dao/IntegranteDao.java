package com.ciandt.internstellarapi.dao;

import com.ciandt.internstellarapi.entity.Integrante;

import java.util.List;

import static com.ciandt.internstellarapi.util.OfyService.ofy;

/**
 * Created by falcao on 06/10/16.
 */

public class IntegranteDao extends GenericDao<Integrante> {

    public List<Integrante> insertMany(List<Integrante> integrantesToSave) {
        ofy().save().entities(integrantesToSave).now();
        return integrantesToSave;
    }
}
