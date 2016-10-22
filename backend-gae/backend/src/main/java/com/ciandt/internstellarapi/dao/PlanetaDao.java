package com.ciandt.internstellarapi.dao;

import com.ciandt.internstellarapi.entity.Planeta;

import java.util.List;

import static com.ciandt.internstellarapi.util.OfyService.ofy;

/**
 * Created by helder on 07/10/16.
 */

public class PlanetaDao extends GenericDao<Planeta> {

    public List<Planeta> saveMany(List<Planeta> planetasToSave) {
        ofy().save().entities(planetasToSave).now();
        return planetasToSave;
    }

}
