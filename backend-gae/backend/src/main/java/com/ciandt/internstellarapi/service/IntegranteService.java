package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.IntegranteDao;

/**
 * Created by falcao on 06/10/16.
 */

public class IntegranteService {

    private IntegranteDao integranteDao;

    public IntegranteService(){
        integranteDao = new IntegranteDao();
    }
}
